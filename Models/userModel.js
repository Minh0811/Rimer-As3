import mongoose from "mongoose";

const userSchema = new mongoose.Schema({
  email: {
    type: String,
    required: true,
    unique: true,
  },
  name: {
    type: String,
    required: true,
  },
  password: {
    type: String,
    required: true,
  },
  userType: {
    type: String,
    required: true,
    enum: ["user", "driver"],
  },
  onGoingBooking: {
    type: mongoose.Schema.Types.ObjectId,
    ref: "Booking", 
    defautl: null, 
  },
  bookingsHistory: [
    {
      type: mongoose.Schema.Types.ObjectId,
      ref: "Booking",
    },
  ],
});


const User = mongoose.model("User", userSchema);

export default User;
