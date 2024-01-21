import express from "express";
import {
  updateUsername,
  getUserDetails,
} from "../controllers/userController.js";

// Create a router
const router = express.Router();

// Route for updating username
router.post("/update-username", updateUsername);
// Route to get user details by ID
router.get('/details/:userId', getUserDetails);

// Export the router
export default router;
