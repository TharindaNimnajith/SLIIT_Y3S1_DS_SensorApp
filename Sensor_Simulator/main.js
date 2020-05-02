generateRandomNumber = (min, max) => {
  return Math.floor(Math.random() * (max - min + 1) + min);
};

updateDetails = () => {
  let co2Level;
  let smokeLevel;

  co2Level = generateRandomNumber(1, 10);
  smokeLevel = generateRandomNumber(1, 10);

  const sensor = {
    co2Level,
    smokeLevel,
  };

  $.ajax({
    url: "http://localhost:5000/api/sensor/2",
    type: "PUT",
    contentType: "application/json",
    dataType: "json",
    data: JSON.stringify(sensor),
    success: function (data) {
      console.log(data);

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

updateDetails();
setInterval(updateDetails, 10000);
