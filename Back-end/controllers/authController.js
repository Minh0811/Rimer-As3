import bcrypt from "bcryptjs";
import jwt  from "jsonwebtoken";

import User from "../Models/userModel.js";

const register = async (req, res) => {
   try {
    const {email, name, password} = req.body;

    if (!email || !name || !password){
        return res.status(400).json({message: "Incomplete data."});
    }

    const userExists = await User.findOne({email});

    if(userExists){
        return res.status(400).json({message: "User already exists"});
    }

    const salt = await bcrypt.genSalt();

    const hashedPassword = await bcrypt.hash(password, salt);

    const newUser = await User.create({
        name,
        email,
        password: hashedPassword
    })

    const token = jwt.sign({userId: newUser._id}, process.env.JWT_SECRET, {
        expiresIn: "1d",
    })

    setJwtCookie(res, token);

    res.status(201);
    
   } catch (error) {
    
   }

}
export {register};