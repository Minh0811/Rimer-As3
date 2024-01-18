import User from "../Models/userModel.js";
import { handleServerError } from "../utils/functions.js";

// Function to update the username
const updateUsername = async (req, res) => {
  try {
    const { userId, newName } = req.body;
    if (!userId || !newName) {
      return res.status(400).json({ message: "Incomplete data." });
    }

    const user = await User.findById(userId);
    if (!user) {
      return res.status(404).json({ message: "User not found" });
    }

    user.name = newName;
    await user.save();

    res.status(200).json({
      _id: user._id,
      name: user.name,
      email: user.email,
      userType: user.userType,
    });
  } catch (error) {
    handleServerError(res, error);
  }
};

// Export the function
export { updateUsername };
