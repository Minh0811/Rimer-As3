import Booking from "../Models/bookingModel.js"
import User from "../Models/userModel.js"

  //ADD A BOOKING
const createBooking = async (req, res) => {
    try {
      const newBooking = new Booking(req.body);
      const savedBooking = await newBooking.save();
      if (req.body.user) {
        const user = User.findById(req.body.user);
        await user.updateOne({ $push: { onGoingBooking: savedBooking._id } });
      }
      if (req.body.driver) {
        const driver = User.findById(req.body.driver);
        await driver.updateOne({ $push: { onGoingBooking: savedBooking._id } });
      }
      res.status(200).json(savedBooking);
    } catch (err) {
      res.status(500).json(err);
    }
};

//GET ALL BOOKINGS
const getAllBookings = async (req, res) => {
  try {
    const allBookings = await Booking.find();
    res.status(200).json(allBookings);
  } catch (err) {
    res.status(500).json(err);
  }
};

//GET A BOOKING
const getABooking = async (req, res) => {
  try {
    const booking = await Booking.findById(req.params.id).populate("user", "driver");
    res.status(200).json(booking);
  } catch (err) {
    res.status(500).json(err);
  }
};

//UPDATE BOOKING
const updateBooking = async (req, res) => {
  try {
    const booking = await Booking.findById(req.params.id);
    await booking.updateOne({ $set: req.body });
    res.status(200).json("Updated successfully!");
  } catch (err) {
    res.status(500).json(err);
  }
};

//DELETE BOOKING
const deleteBooking = async (req, res) => {
  try {
    await User.updateMany(
      { onGoingBooking: req.params.id },
      { $pull: { onGoingBooking: req.params.id } }
    );
    await Booking.findByIdAndDelete(req.params.id);
    res.status(200).json("Deleted successfully");
  } catch (err) {
    res.status(500).json(err);
  }
};

export {createBooking, getAllBookings, getABooking, updateBooking, deleteBooking};