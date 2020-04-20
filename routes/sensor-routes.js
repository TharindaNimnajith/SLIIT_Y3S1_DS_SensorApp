const express = require("express");
const router = express.Router();
const Sensor = require("../models/sensor-model");

router.post("/sensor", (req, res, next) => {
  Sensor.create(req.body)
    .then((sensor) => {
      res.send(sensor);
    })
    .catch(next);
});

router.put("/sensor/:id", (req, res, next) => {
  Sensor.findOneAndUpdate(
    { id: req.params.id },
    { $set: req.body },
    { new: true },
    (error, doc) => {
      res.send(doc);
    }
  );
});

router.get("/sensor", (req, res, next) => {
  Sensor.find({}, (err, sensors) => {
    var sensorMap = {};

    sensors.forEach((sensor) => {
      sensorMap[sensor.id] = sensor;
    });

    res.send(sensorMap);
  }).catch(next);
});

router.get("/sensor/:id", (req, res, next) => {
  Sensor.find({ id: req.params.id }, (err, sensors) => {
    var sensorMap = {};

    sensors.forEach((sensor) => {
      sensorMap[sensor.id] = sensor;
    });

    res.send(sensorMap);
  }).catch(next);
});

module.exports = router;
