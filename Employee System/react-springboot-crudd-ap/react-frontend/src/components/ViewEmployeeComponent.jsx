import React, { Component } from "react";
import EmployeeService from "../services/EmployeeService";

class ViewEmployeeComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      id: this.props.match.params.id,
      firstName: "",
      lastName: "",
      emailId: "",
    };
  }

  componentDidMount() {
    EmployeeService.getEmployee(this.state.id).then((res) => {
      console.log(res.data);
      let employee = res.data;
      this.setState({
        firstName: employee.firstName,
        lastName: employee.lastName,
        emailId: employee.emailId,
      });
    });
  }

  render() {
    return (
      <div className="card" style={{ marginTop: "10px" }}>
        <div className="card-body">
          <h4 className="card-title"> Employee Information </h4>
          <p className="card-text"> First Name: {this.state.firstName}</p>
          <p className="card-text"> Last Name: {this.state.lastName}</p>
          <p className="card-text"> Email: {this.state.emailId}</p>
        </div>
      </div>
    );
  }
}

export default ViewEmployeeComponent;
