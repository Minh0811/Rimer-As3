import express from "express";
import { register, login, logout, getUser } from "../controllers/authController.js";
import { auth } from "../middlewares/auth.js"

const router = express.Router();
router.post("/register", register);
router.post("/login", login);
// router.post("/authGoogle", loginByGoogle);
router.post("/logout", logout);
router.get("/info", auth, getUser);

export default router;
