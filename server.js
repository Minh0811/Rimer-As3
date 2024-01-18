import express from "express";
import dotenv from "dotenv";
import cookiesParser from "cookie-parser";
import connectDatabase from "./config/database.js";
import authRoutes from "./routes/authRoutes.js"
import bookingRoutes from "./routes/bookingRoutes.js";
import reviewRoutes from "./routes/reviewRoutes.js";
import userRoutes from "./routes/userRoutes.js";
dotenv.config();
connectDatabase();
//Connect to database

const port = process.env.PORT || 3000;

const app = express();

// Middleware setup
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(cookiesParser());

// Use routes
app.use("/api/auth", authRoutes);
app.use("/api/booking", bookingRoutes);
app.use('/api/reviews', reviewRoutes); 
app.use("/user", userRoutes);

app.listen(port, () => console.log(`Server running on port ${port}`));
