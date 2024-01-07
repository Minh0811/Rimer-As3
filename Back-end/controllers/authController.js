import bcrypt from "bcryptjs";
import jwt from "jsonwebtoken";

import User from "../Models/userModel.js";
import { handleServerError, setJwtCookie } from "../utils/functions.js";

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
export { register , login};
