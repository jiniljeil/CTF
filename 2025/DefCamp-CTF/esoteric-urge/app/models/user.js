import mongoose, { Schema } from "mongoose";

const UserSchema = new Schema({
  username: { type: String, unique: true, required: true },
  password: { type: String, required: true },
  role: { type: String, default: "adept", required: true }
});

const User = mongoose.model("User", UserSchema);
export default User;
