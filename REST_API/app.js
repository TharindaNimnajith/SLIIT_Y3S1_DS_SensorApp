const express = require("express");
const mongoose = require("mongoose");
const bodyParser = require("body-parser");
var cors = require("cors");

const app = express();

app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());
app.use(cors());

app.use("/api", require("./routes/sensor-routes"));

app.use(function (err, req, res, next) {
  console.log(err);
  res.status(422).send({error: err.message});
});

mongoose
  .connect(
    "mongodb+srv://ayesh:ayesh@ayesh-mongo-cluster-jqsxb.mongodb.net/sliit-y3s1-ds-restAPI?retryWrites=true&w=majority"
  )
  .then(() => {
    app.listen(5000);
  })
  .catch((error) => {
    console.log(error);
  });
