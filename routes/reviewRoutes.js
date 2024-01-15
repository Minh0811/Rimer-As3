import express from "express";
import {
  createReview,
  getReviewsForDriver,
} from "../controllers/reviewController.js";

const router = express.Router();

// Route for creating a new review
router.post("/create-review", createReview);

// Route for getting all reviews for a specific driver
router.get("/driver-reviews/:driverId", getReviewsForDriver);

export default router;
