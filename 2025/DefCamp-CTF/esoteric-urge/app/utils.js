import mongoose from 'mongoose';
import crypto from 'crypto';
import User from './models/user.js';

const dbUser = process.env.MONGO_USER || 'admin';
const dbPass = process.env.MONGO_PASS || 'admin';

const localDB = `mongodb://${dbUser}:${dbPass}@db:27017/db?authSource=admin`;

export const connectDB = async () => {
  try {
    await mongoose.connect(localDB, {
      useNewUrlParser: true,
      useUnifiedTopology: true,
    });
    console.log("MongoDB Connected");
    return true;
  } catch (e) {
    console.log("Couldn't connect");
    throw e;
  }
};

export function hash(password) {
  return crypto.createHash('sha256').update(password).digest('hex');
}

export function sleep(s) {
  return new Promise(resolve => setTimeout(resolve, s * 1000));
}

export async function beginProjection() {
  const metatron = await User.findOne({ username: 'metatron' });
  if (metatron) {
    await User.deleteOne({ username: 'metatron' });
  }
  await User.create({
    username: 'metatron',
    password: hash(process.env.ADMIN_PASSWD || "lordshiva42"),
    role: 'guide'
  });
}
