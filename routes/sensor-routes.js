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

router.delete("/sensor/:id", (req, res, next) => {
  Sensor.deleteOne({ id: req.params.id }, (err, result) => {
    // if (err) {
    //   res.send(err);
    //   // res.json({
    //   //   error: `error ${err}`,
    //   // });
    // } else {
    //   res.send(result);
    //   // res.json({
    //   //   message: `deleted ${req.params.id}`,
    //   // });
    // }

    if (result.deletedCount) {
      res.json({
        message: `deleted ${req.params.id}`,
      });
    } else {
      res.json({
        message: `deleted failed ${req.params.id}`,
      });
    }
  }).catch(next);
});

module.exports = router;
