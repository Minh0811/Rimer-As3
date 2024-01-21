import express from "express";
import {
  createBooking,
  updateBooking,
  getAllBookings,
  getABooking,
  deleteBooking,
  getDriverBookings,
  checkDriverResponse,
} from "../controllers/bookingController.js";

const router = express.Router();

// Booking routes
router.post("/create", createBooking);
router.put("/:id", updateBooking);
router.get("/", getAllBookings);
router.get("/:id", getABooking);
router.delete("/:id", deleteBooking);
router.get("/driver/:driverId", getDriverBookings);
router.get("/check-response/:bookingId", checkDriverResponse);
router.get("/check-booking-status/:bookingId", checkDriverResponse);


export default router;