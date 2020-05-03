// dummy sensor simulator

// method implementation for generating a random number
generateRandomNumber = (min, max) => {
  return Math.floor(Math.random() * (max - min + 1) + min);
};

// update details method implementation
updateDetails = () => {
  let co2Level;
  let smokeLevel;

  // generate random values from 1 to 10 for co2 level and smoke level
  co2Level = generateRandomNumber(1, 10);
  smokeLevel = generateRandomNumber(1, 10);

  const sensor = {
    co2Level,
    smokeLevel,
  };

  // sending a put http request to update the values of co2 level and smoke level
  $.ajax({
    url: "http://localhost:5000/api/sensor/2",
    type: "PUT",
    contentType: "application/json",
    dataType: "json",
    data: JSON.stringify(sensor),
    success: function (data) {
      if (data) {
        $(".sensorId").text(data.id);
        $(".name").text(data.name);
        $(".co2Level").text(data.co2Level);
        $(".smokeLevel").text(data.smokeLevel);
        $(".roomNo").text(data.roomNo);
        $(".floorNo").text(data.floorNo);
      }
    },
  });
};

// calling update details method
updateDetails();
// call update details method every 10 seconds
setInterval(updateDetails, 10000);
