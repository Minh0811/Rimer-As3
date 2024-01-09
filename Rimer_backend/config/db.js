const mongoose = require('mongoose');

const db = "mongodb+srv://hagiangnguyen2705:Giang270599@rimer.xwyszq8.mongodb.net/"

mongoose.connect(db)
.then(() => {
    console.log("Database connected")
})
.catch(err => {
    console.log(err)
})