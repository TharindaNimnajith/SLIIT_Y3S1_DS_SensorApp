import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router } from "react-router-dom";

import SensorList from "./components/sensor-list-component";

function App() {
  return (
    <Router>
      <div className="container">
        <SensorList />
      </div>
    </Router>
  );
}

export default App;
