const express = require("express");
const router = express.Router();
const Sensor = require("../models/sensor-model");
const nodeailer = require("nodemailer");

require("dotenv").config();

let transporter = nodeailer.createTransport({
  service: "gmail",
  auth: {
    user: "ayeshlak1998@gmail.com",
    pass: process.env.PASSWORD,
  },
});

router.post("/sensor", (req, res, next) => {
  Sensor.create(req.body)
    .then((sensor) => {
      res.send(sensor);
    })
    .catch(next);
});

router.put("/sensor/:id", (req, res, next) => {
  Sensor.findOneAndUpdate(
    {id: req.params.id},
    {$set: req.body},
    {new: true},
    (error, doc) => {
      if (doc.co2Level > 5 && doc.smokeLevel > 5) {
        let info = {
          from: '"Sensor Alerts 💥🔥" ayeshlak1998@gmail.com',
          to: "aruniprashani@gmail.com",
          subject: "SmokeLevel and CO2 level Increased",
          text: `SmokeLevel of the sensor ${doc.id} increased to ${doc.smokeLevel}  and CO2 Level of the sensor ${doc.id} increased to ${doc.co2Level}`,
        };
        transporter.sendMail(info, (err, data) => {
          if (err) {
            console.log(err);
          } else {
            console.log("sent");
          }
        });
        console.log("message has been sent to 077465521");
        console.log(doc.co2Level);
      } else if (doc.co2Level > 5) {
        let info = {
          from: '"Sensor Alerts 💥🔥" ayeshlak1998@gmail.com',
          to: "aruniprashani@gmail.com",
          subject: "CO2 Level Increased",
          text: `CO2 Level of the sensor ${doc.id} increased to ${doc.co2Level}`,
        };
        transporter.sendMail(info, (err, data) => {
          if (err) {
            console.log(err);
          } else {
            console.log("sent");
          }
        });
        console.log("message has been sent to 077465521");
        console.log(doc.co2Level);
      } else if (doc.smokeLevel > 5) {
        let info = {
          from: '"Sensor Alerts 💥🔥" ayeshlak1998@gmail.com',
          to: "aruniprashani@gmail.com",
          subject: "SmokeLevel Increased",
          text: `SmokeLevel of the sensor ${doc.id} increased to ${doc.smokeLevel}`,
        };
        transporter.sendMail(info, (err, data) => {
          if (err) {
            console.log(err);
          } else {
            console.log("sent");
          }
        });
        console.log("message has been sent to 077465521");
        console.log(doc.smokeLevel);
      }
      res.send(doc);
    }
  );
});

router.get("/sensor", (req, res, next) => {
  Sensor.find({}, (err, sensors) => {
    res.send(sensors);
  }).catch(next);
});

router.get("/sensors", (req, res, next) => {
  Sensor.find({}, (err, sensors) => {
    var sensorMap = {};
    sensors.forEach((sensor) => {
      sensorMap[sensor.id] = sensor;
    });
    res.send(sensorMap);
  }).catch(next);
});

router.get("/sensor/:id", (req, res, next) => {
  Sensor.find({id: req.params.id}, (err, sensors) => {
    var sensorMap = {};
    sensors.forEach((sensor) => {
      sensorMap[sensor.id] = sensor;
    });
    res.send(sensorMap);
  }).catch(next);
});

router.delete("/sensor/:id", (req, res, next) => {
  Sensor.deleteOne({id: req.params.id}, (err, result) => {
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
