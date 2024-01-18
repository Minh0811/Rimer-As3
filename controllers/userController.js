import User from "../Models/userModel.js";
import { handleServerError } from "../utils/functions.js";

// Function to update the username
const updateUsername = async (req, res) => {
  try {
    const { userId, newName } = req.body;
    console.log(
      "Update request received for userId:",
      userId,
      "with newName:",
      newName
    ); // Log the input

    if (!userId || !newName) {
      console.log("Incomplete data.");
      return res.status(400).json({ message: "Incomplete data." });
    }

    const user = await User.findById(userId);
    if (!user) {
      console.log("User not found for userId:", userId);
      return res.status(404).json({ message: "User not found" });
    }

    console.log("Current username:", user.name);
    user.name = newName;
    await user.save();
    console.log("Updated username:", user.name);

    res.status(200).json({
      _id: user._id,
      name: user.name,
      email: user.email,
      userType: user.userType,
    });
  } catch (error) {
    console.error("Error updating username:", error); // Log the error
    handleServerError(res, error);
  }
};


// Export the function
export { updateUsername };
