import React, { Component } from 'react';
import { Redirect ,withRouter } from "react-router-dom";
import axios from 'axios';
import './Login.css'
import '../tools/vendor/bootstrap/css/bootstrap.min.css'
import '../tools/fonts/font-awesome-4.7.0/css/font-awesome.min.css'
import '../tools/fonts/Linearicons-Free-v1.0.0/icon-font.min.css'
import '../tools/vendor/animate/animate.css'
import '../tools/vendor/css-hamburgers/hamburgers.min.css'
import '../tools/vendor/animsition/css/animsition.min.css'
import '../tools/vendor/select2/select2.min.css'
import '../tools/vendor/daterangepicker/daterangepicker.css'
import '../tools/css/util.css'
import '../tools/css/main.css'

class Login extends Component {

	constructor(props){
    super(props);
    this.state = {
     postStatus :"200",
     msg:"",
     flag:"2",
     token :""
	};
}
  onSubmit =(event)=>{
			
    event.preventDefault();
 
    axios.put(`http://localhost:8080/jobonja_war_exploded/Login?userName=${event.target.username.value}&passWord=${event.target.pass.value}`).then((response)=> {
        
        this.setState({postStatus : response.status , msg : response.data.msg ,flag :response.data.flag ,token :response.data.access_token}
         )
      },error=>{
        console.log(error)
        this.setState({postStatus : '404' , msg : "server not found"} )
      })
  }
  render() {
		console.log('tokennnn',this.state.token)
    localStorage.setItem('token',this.state.token);
    if( this.state.flag ==="0")
      alert(this.state.msg)
    return (
      this.state.flag ==="1"? 
      <Redirect to="/home" />
      :
        <div>
          <div className="top"></div>
          <div className ="middle">

					<div className="limiter">
		<div className="container-login100">
			<div className="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
				<form className="login100-form validate-form flex-sb flex-w" onSubmit={this.onSubmit} >
					<span className="login100-form-title p-b-32">
						Account Login
					</span>

					<span className="txt1 p-b-11">
						Username
					</span>
					<div className="wrap-input100 validate-input m-b-36" data-validate = "Username is required">
						<input className="input100" type="text" name="username" />
						<span className="focus-input100"></span>
					</div>
					
					<span className="txt1 p-b-11">
						Password
					</span>
					<div className="wrap-input100 validate-input m-b-12" data-validate = "Password is required">
						<span className="btn-show-pass">
							<i className="fa fa-eye"></i>
						</span>
						<input className="input100" type="password" name="pass" />
						<span className="focus-input100"></span>
					</div>
					
					<div className="flex-sb-m w-full p-b-48">
						<div className="contact100-form-checkbox">
							<input className="input-checkbox100" id="ckb1" type="checkbox" name="remember-me"/>
							<label className="label-checkbox100" >
								Remember me
							</label>
						</div>

						<div>
							<a href="#" className="txt3">
								Forgot Password?
							</a>
						</div>
					</div>

					<div className="container-login100-form-btn">
            <input type="submit" className="login100-form-btn" value="Login " />
					</div>

				</form>
			</div>
		</div>
	</div>

	
        
		</div>
	</div>

    );
  }
}

export default withRouter(Login);