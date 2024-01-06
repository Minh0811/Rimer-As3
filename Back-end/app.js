const express = require("express");
const dotenv = require("dotenv");
const cookiesParser = require("cookie-parser");

dotenv.config();

//Connect to database

const port = process.env.PORT;

const app = express();

app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(cookiesParser());

app.listen(port, () => console.log(`Server running on port ${port}`));

