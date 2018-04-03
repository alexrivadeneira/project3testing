import React, { Component } from 'react';

class UserNewForm extends Component{
	
	state = {
		newUser: {}
	}

	handleSubmit = (event) => {
		event.preventDefault();
		this.props.createUser(this.state.newUser);
	}

	handleChange = (event) => {
		const updatedProperty = event.target.name;
		const newValue = event.target.value

		const updatedUser = { ...this.state.newUser}
		updatedUser[updatedProperty] = newValue;
		this.setState({newUser: updatedUser});
	}

	render(){
		return(
			<div>
				<h2>Make New User</h2>
				<form>
					<div>
						<h4>UserName</h4>
						<input
							id="new-user-userName"
							name="userName"
							type="text"
							onChange={this.handleChange}
						/>
						<h4>First Name</h4>
						<input 
							id="new-user-firstName"
							name="firstName"
							type="text"
							onChange={this.handleChange}
						/>	
						<h4>Last Name</h4>


						<input
							id="new-user-lastName"
							name="lastName"
							type="text"
							onChange={this.handleChange}

						/>	
					</div>
					<div>
						<button
							id="new-user-submit"
							value="Submit"
							onClick={this.handleSubmit}
						>
							Submit!
						</button>
					</div>									
				</form>
			</div>
		);
	}
}

export default UserNewForm;