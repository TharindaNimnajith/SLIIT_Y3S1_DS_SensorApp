import React, {Component} from "react";
import axios from "axios";

// sensor list component
class SensorList extends Component {
  constructor(props) {
    super(props);

    this.state = {
      sensors: [
        {
          id: "2",
          name: "abc",
          active: true,
          floorNo: 3,
          roomNo: 1,
          smokeLevel: 4,
          co2Level: 3,
        },
        {
          id: "4",
          name: "xyz",
          active: false,
          floorNo: 1,
          roomNo: 2,
          smokeLevel: 3,
          co2Level: 2,
        },
      ],
    };
  }

  // get sensor list from mongodb via the express api
  getSensorList() {
    axios
      .get("http://localhost:5000/api/sensor")
      .then((response) => {
        this.setState({sensors: [...response.data]});
        console.log("response");
      })
      .catch((err) => {
        console.log(`error is ${err}`);
      });
  }

  // refresh table every 40 seconds
  componentDidMount() {
    this.interval = setInterval(() => this.getSensorList(), 40000);
  }

  componentWillUnmount() {
    clearInterval(this.interval);
  }

  // display the table and values of each sensor
  // if co2 level or smoke level of a sensor is greater than 5, color that row in red
  render() {
    return (
      <div className="container" style={{marginTop: "50px"}}>
        <h1 style={{textAlign: "center", padding: "15px", color: "#343a40"}}>
          Sensor Details
        </h1>
        <table className="table table-bordered">
          <thead className="thead-dark">
          <tr style={{textAlign: "center"}}>
            <th>Sensor ID</th>
            <th>Sensor Name</th>
            <th>Active Status</th>
            <th>Floor Number</th>
            <th>Room Number</th>
            <th>Smoke Level (1-10)</th>
            <th>CO2 Level (1-10)</th>
          </tr>
          </thead>
          <tbody>
          {this.state.sensors.map((sensor) => {
            return sensor.smokeLevel > 5 || sensor.co2Level > 5 ? (
              <tr
                bgcolor="#FF0000"
                style={{color: "white"}}
                key={sensor.id}
              >
                <td>{sensor.id}</td>
                <td>{sensor.name}</td>
                <td>{sensor.active ? "Active" : "Not Active"}</td>
                <td>{sensor.floorNo}</td>
                <td>{sensor.roomNo}</td>
                <td>{sensor.smokeLevel}</td>
                <td>{sensor.co2Level}</td>
              </tr>
            ) : (
              <tr bgcolor="White" key={sensor.id}>
                <td>{sensor.id}</td>
                <td>{sensor.name}</td>
                <td>{sensor.active ? "Active" : "Not Active"}</td>
                <td>{sensor.floorNo}</td>
                <td>{sensor.roomNo}</td>
                <td>{sensor.smokeLevel}</td>
                <td>{sensor.co2Level}</td>
              </tr>
            );
          })}
          </tbody>
        </table>
      </div>
    );
  }
}

export default SensorList;
