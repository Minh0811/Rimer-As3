import Booking from "../Models/bookingModel.js"
import User from "../Models/userModel.js"

//ADD A BOOKING
const createBooking = async (req, res) => {
    try {
      const newBooking = new Booking(req.body);
      const savedBooking = await newBooking.save();
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
    const booking = await Booking.findById(req.params.id);
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


const deleteBooking = async (req, res) => {
  try {
    await Booking.findByIdAndDelete(req.params.id);
    res.status(200).json("Deleted successfully");
  } catch (err) {
    res.status(500).json(err);
  }
};


// GET all bookings for a specific driver
const getDriverBookings = async (req, res) => {
  try {
    const driverId = req.params.driverId;
    const bookings = await Booking.find({ driver: driverId });
    console.log(bookings);
    res.status(200).json(bookings);
  } catch (err) {
    res.status(500).json(err);
  }
};




const checkDriverResponse = async (req, res) => {
  try {
    const { bookingId } = req.params;
    console.log(`Checking driver response for booking ID: ${bookingId}`);

    const booking = await Booking.findById(bookingId);
    console.log(`Booking found:`, booking);

    if (!booking) {
      console.log(`Booking not found for ID: ${bookingId}`);
      return res.status(404).json({ message: "Booking not found" });
    }

    // Send the booking status
    console.log(
      `Sending response: Accepted - ${
        booking.status === "accepted"
      }, Status - ${booking.status}`
    );
    res.status(200).json({
      accepted: booking.status === "accepted",
      status: booking.status,
    });
  } catch (error) {
    console.error("Error checking driver response:", error);
    handleServerError(res, error);
  }
};


const checkBookingStatus = async (req, res) => {
  try {
    const { bookingId } = req.params;
    const booking = await Booking.findById(bookingId);

    if (!booking) {
      return res.status(404).json({ message: "Booking not found" });
    }

    // Send the booking status
    res.status(200).json({
      completed: booking.status === "completed",
      status: booking.status,
    });
  } catch (error) {
    console.error("Error checking booking status:", error);
    handleServerError(res, error);
  }
};

export {
  getDriverBookings,
  createBooking,
  getAllBookings,
  getABooking,
  updateBooking,
  deleteBooking,
  checkDriverResponse,
  checkBookingStatus,
};