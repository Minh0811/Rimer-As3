import express from "express";
import {
  registerUser,
  registerDriver,
  login,
  logout, getUser,
} from "../controllers/authController.js";
import { auth } from "../middlewares/auth.js"


const router = express.Router();

// Separate registration routes for user and driver
router.post("/register/user", registerUser);
router.post("/register/driver", registerDriver);

// Existing login and logout routes
router.post("/login", login);
router.post("/logout", logout);
router.get("/info", auth, getUser);

export default router;
