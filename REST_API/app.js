const express = require("express");
const mongoose = require("mongoose");
const bodyParser = require("body-parser");
var cors = require("cors");

require("dotenv").config();

const app = express();

app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());
app.use(cors());

// defining the route
app.use("/api", require("./routes/sensor-routes"));

app.use(function (err, req, res, next) {
  console.log(err);
  res.status(422).send({error: err.message});
});

// getting the mongodb atlas uri from the .env file
const uri = process.env.MONGO_ATLAS;

const options = {
  useNewUrlParser: true,
  useUnifiedTopology: true,
  useCreateIndex: true
};

// connecting to mongodb with mongoose
mongoose
  .connect(uri, options)
  .then(() => {
    app.listen(5000);
  })
  .catch((error) => {
    console.log(error);
  });
