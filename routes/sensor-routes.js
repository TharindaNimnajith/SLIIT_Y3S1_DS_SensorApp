const express = require("express");
const router = express.Router();
const Sensor = require("../models/sensor-model");

// add a new sensor to the db
router.post("/sensor", (req, res, next) => {
  Sensor.create(req.body)
    .then((sensor) => {
      res.send(sensor);
    })
    .catch(next);
});

module.exports = router;
