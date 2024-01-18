import express from "express";
import { createBooking, updateBooking, getAllBookings, getABooking, deleteBooking, getDriverBookings } from "../controllers/bookingController.js";

const router = express.Router();

// Booking routes
router.post("/create", createBooking);
router.put("/:id", updateBooking);
router.get("/", getAllBookings);
router.get("/:id", getABooking);
router.delete("/:id", deleteBooking);
router.get("/driver/:driverId", getDriverBookings);
export default router;