// controllers/reviewController.js
import Review from "../Models/reviewModel.js";

export const createReview = async (req, res) => {
  try {
    const review = await Review.create(req.body);
    res.status(201).json(review);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

export const getReviewsForDriver = async (req, res) => {
  try {
    const reviews = await Review.find({ driverId: req.params.driverId });
    res.status(200).json(reviews);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};
