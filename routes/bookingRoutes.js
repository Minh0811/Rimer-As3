import express from "express";
import { createBooking, updateBooking, getAllBookings, getABooking, deleteBooking , getUserAppendingBooking} from "../controllers/bookingController.js";

const router = express.Router();

// Booking routes
router.post("/create", createBooking);
router.put("/:id", updateBooking);
router.get("/", getAllBookings);
router.get("/:id", getABooking);
router.delete("/:id", deleteBooking);
router.get("/user/getAppendingBooking/:userId", getUserAppendingBooking);

export default router;