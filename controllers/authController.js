import bcrypt from "bcryptjs";
import jwt from "jsonwebtoken";

import User from "../Models/userModel.js";
import { handleServerError, setJwtCookie } from "../utils/functions.js";


const { OAuth2Client } = require('google-auth-library')
const clientId = process.env.GOOGLE_CLIENT_ID;
const authClient = new OAuth2Client(clientId)

const register = async (req, res) => {
  try {
    const { email, name, password } = req.body;

    if (!email || !name || !password) {
      return res.status(400).json({ message: "Incomplete data." });
    }

    const userExists = await User.findOne({ email });

    if (userExists) {
      return res.status(400).json({ message: "User already exists" });
    }

    const salt = await bcrypt.genSalt();

    const hashedPassword = await bcrypt.hash(password, salt);

    const newUser = await User.create({
      name,
      email,
      password: hashedPassword,
    });

    console.log("JWT Secret:", process.env.JWT_SECRET);
    const token = jwt.sign({ userId: newUser._id }, process.env.JWT_SECRET, {
      expiresIn: "1d",
    });

    setJwtCookie(res, token);

    res.status(201).json({
      _id: newUser._id,
      name: newUser.name,
      email: newUser.email,
    });
  } catch (error) {
    handleServerError(res, error);
  }
};

const login = async (req, res) => {
  try {
    const { email, password } = req.body;
    if (!email || !password) {
      return res.status(400).json({ message: "Incomplete data." });
    }

    const user = await User.findOne({ email });

    if (user) {
      const passwordMatch = await bcrypt.compare(password, user.password);

      if (!passwordMatch) {
        return res.status(401).json({ message: "Invalid email or password" });
      }

      const jwtCookie = req.cookies["jwt-cookie"];

      if (jwtCookie) {
        return res
          .status(409)
          .json({ message: "An user is already authenticated" });
      }

      const token = jwt.sign({userId: user._id}, process.env.JWT_SECRET, {
        expiresIn: "1d",
      })

      setJwtCookie(res, token);

      res.status(200).json({
        _id: user._id,
        name: user.name,
        email: user.email,
      });
      
    } else {
        return res.status(401).json({ message: "Invalid email or password"})
    }
  } catch (error) {
    handleServerError(res, error);
  }
};

const loginByGoogle = async (req, res) => {
  try {
    const { idToken } = req.body;
    if (idToken) {
        authClient.verifyIdToken({ idToken, audience: clientId })
            .then(async response => {
                const { email_verified, email, name } = response.payload
                if (email_verified) {
                    const user = await User.findOne({ email });
                        if(user){
                            return res.json({
                                message: email,
                                name: user.name,
                                email: user.email
                              });
                        }
                        else{
                            let pass = email + clientId
                            const newUser = new User({
                                name,
                                email,
                                password: pass,
                            });

                            newUser.save()
                            
                            res.status(200).json({
                                name: newUser.name,
                                email: newUser.email,
                            });
                        }
                }
            })
            .catch(err => { console.log(err) })
    }
  } catch (error) {
    handleServerError(res, error)
  }
}

const logout = async (req, res) => {
    try {
        res.cookie('jwt-cookie', "", {
            httpOnly: true,
            expires: new Date(0)
        });

        res.status(200).json({ message: "User logged out."})
    } catch (error) {
        handleServerError(res, error);
    }
}
export { register , login, loginByGoogle, logout};
