import mongoose from "mongoose";

const reviewSchema = new mongoose.Schema({
  driverId: {
    type: mongoose.Schema.Types.ObjectId,
    ref: "User",
    required: true,
  },
  userId: {
    type: mongoose.Schema.Types.ObjectId,
    ref: "User",
    required: true,
  },
  rating: {
    type: Number,
    required: false,
    min: 1,
    max: 5,
  },
  reviewText: {
    type: String,
    required: false,
  },
  timestamp: {
    type: Date,
    default: Date.now,
    required: false,
  },
});

const Review = mongoose.model("Review", reviewSchema);

export default Review;
