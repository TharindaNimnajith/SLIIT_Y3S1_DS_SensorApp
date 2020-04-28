import React, {Component} from "react";
import axios from "axios";

class SensorList extends Component {
  constructor(props) {
    super(props);

    this.state = {
      sensors: [
        {
          id: "2",
          active: true,
          floorNo: 3,
          roomNo: 1,
          smokeLevel: 4,
          co2Level: 6,
        },
        {
          id: "4",
          active: false,
          floorNo: 1,
          roomNo: 2,
          smokeLevel: 3,
          co2Level: 2,
        }
      ],
    };
  }

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

  componentDidMount() {
    this.interval = setInterval(() => this.getSensorList(), 40000);
  }

  componentWillUnmount() {
    clearInterval(this.interval);
  }

  render() {
    return (
      <div>
        <h1>Sensor Details</h1>
        <table className="table table-bordered  ">
          <thead className="thead-light">
          <tr>
            <th>Sensor ID</th>
            <th>Active Status</th>
            <th>Floor Number</th>
            <th>Room Number</th>
            <th>Smoke Level (1-10)</th>
            <th>CO2 Level (1-10)</th>
          </tr>
          </thead>
          <tbody>
          {this.state.sensors.map(sensor => {
              return (sensor.smokeLevel > 5 || sensor.co2Level > 5) ? (
                <tr bgcolor="#FF0000" style={{color: 'White'}} key={sensor.id}>
                  <td>{sensor.id}</td>
                  <td>{sensor.active ? "Active" : "Not Active"}</td>
                  <td>{sensor.floorNo}</td>
                  <td>{sensor.roomNo}</td>
                  <td>{sensor.smokeLevel}</td>
                  <td>{sensor.co2Level}</td>
                </tr>) : (<tr bgcolor='White' key={sensor.id}>
                <td>{sensor.id}</td>
                <td>{sensor.active ? "Active" : "Not Active"}</td>
                <td>{sensor.floorNo}</td>
                <td>{sensor.roomNo}</td>
                <td>{sensor.smokeLevel}</td>
                <td>{sensor.co2Level}</td>
              </tr>)
            }
          )}
          </tbody>
        </table>
      </div>
    );
  }
}

export default SensorList;
