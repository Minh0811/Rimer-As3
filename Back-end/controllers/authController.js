import bcrypt from "bcryptjs";
import Jwt from "jsonwebtoken";

const register = async (req, res) => {
    res.json({message: "Register endpoint works."})

}
export {register};