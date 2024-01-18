import express from "express";
import { updateUsername } from "../controllers/userController.js";

// Create a router
const router = express.Router();

// Route for updating username
router.post("/update-username", updateUsername);

// Export the router
export default router;
