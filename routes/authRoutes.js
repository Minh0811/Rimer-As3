import express from "express";
import { register, login, logout, loginByGoogle } from "../controllers/authController.js";

const router = express.Router();
router.post("/register", register);
router.post("/login", login);
router.post("/authGoogle", loginByGoogle);
router.post("/logout", logout);

export default router;
