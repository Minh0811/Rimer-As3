const express = require('express');
const cors = require('cors');
require("./config/db");
const authRoutes = require("./routes/UserRoutes")

const app = express();
app.use(cors());
app.use(express.json());
app.use(authRoutes);

app.listen(8000, () => {
    console.log("Server is running on port 8000")
})