import express from "express";
import dotenv from "dotenv";
import cookiesParser from "cookie-parser";
import connectDatabase from "./config/database.js";
import authRoutes from "./routes/authRoutes.js";
import bookingRoutes from "./routes/bookingRoutes.js";
dotenv.config();
connectDatabase();
//Connect to database

const port = process.env.PORT || 3000;

const app = express();

app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(cookiesParser());

app.use("/api/auth", authRoutes);
app.use("/api/booking", bookingRoutes);

app.listen(port, () => console.log(`Server running on port ${port}`));
