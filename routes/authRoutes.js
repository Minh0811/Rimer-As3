import express from "express";
import {
  registerUser,
  registerDriver,
  login,
  logout,
  getAllDrivers,
} from "../controllers/authController.js";

const router = express.Router();

// Separate registration routes for user and driver
router.post("/register/user", registerUser);
router.post("/register/driver", registerDriver);

// Existing login and logout routes
router.post("/login", login);
router.post("/logout", logout);

// Get all drivers
router.get("/getDrivers", getAllDrivers)

export default router;
