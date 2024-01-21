import mongoose from "mongoose";

const bookingSchema = new mongoose.Schema({
  user: {
    type: String,
    required: true,
  },
  driver: {
    type: String,
    required: true,
  },
  status: {
    type: String,
    enum: ["pending", "accepted", "declined", "completed"],
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
