const mongoose = require("mongoose");

const Schema = mongoose.Schema;

const sensorSchema = new Schema({
  id: { type: String, required: true, unique: true },
  name: { type: String, required: true },
  floorNo: { type: Number, required: true },
  roomNo: { type: Number, required: true },
  active: { type: Boolean, required: false, default: true },
  smokeLevel: { type: Number, required: true, default: 0 },
  co2Level: { type: Number, required: true, default: 0 }
});

module.exports = mongoose.model("Sensors", sensorSchema);
