import express from "express";
import { register, login, loginByGoogle, logout} from "../controllers/authController.js";

const router = express.Router();
router.post("/register", register);
router.post("/login", login);
router.post("/logout", logout);
router.post("/loginGoogle", loginByGoogle)

export default router;
