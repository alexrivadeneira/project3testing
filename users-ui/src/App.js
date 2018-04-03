import React, { Component } from 'react';
import './App.css';
import UserList from './components/UserList.js';
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

  deleteUser = async (id, index) => {
    try {
      await axios.delete('/users/${id}');
      const updatedUsers = [...this.state.users];
      updatedUsers.splice(index, 1);
      this.setState({users: updatedUsers});
    } catch (error){
      console.log(error);
    }
  }

  render() {
    return (
      <div>
        <UserList users={this.state.users} deleteUser={this.deleteUser}/>
      </div>
    );
  }


}

export default App;
