import mongoose from "mongoose";

const bookingSchema = new mongoose.Schema({
  user: {
    type: mongoose.Schema.Types.ObjectId,
    ref: "User",
  },
  driver: {
    type: mongoose.Schema.Types.ObjectId,
    ref: "User",
  },
  status: {
    type: String,
    enum: ["pending", "accepted", "declined", "completed"], // Add more statuses as needed
    default: "pending",
  },
  distance: {
    type: Number,
    default: 0,
    require: true,
  },
  price: {
    type: Number,
    default: 0,
    require: true,
  },
  startPoint: {
    type: String,
    // require: true,
  },
  endPoint: {
    type: String,
    // require: true,
  },
});


const Booking = mongoose.model("Booking", bookingSchema);

export default Booking;
