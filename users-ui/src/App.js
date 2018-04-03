import React, { Component } from 'react';
import './App.css';
import axios from 'axios';

class App extends Component {
    state = {
      users: [],
    }

  componentDidMount(){
    axios.get("/users")
    .then((response) => {
      this.setState({users: response.data});
    }).catch((error) => {
      console.log("Error getting users");
      console.log(error);
    });
  }

    render(){
      return(
        <div>
          {this.state.users}
        </div>
      );
    }  


}

export default App;
