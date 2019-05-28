import React, { Component } from 'react';
import { Redirect ,withRouter } from "react-router-dom";
import axios from 'axios';
import './Register.css';
import '../tools/css/demo.css';
import '../tools/css/normalize.css';

class Register extends Component {
  
  constructor(props){
    super(props);
    this.state = {
     postStatus :"200",
     msg:"",
     flag:"2",
     token :""
  };
}
  
  registerToServer =(event)=>{
    
    event.preventDefault();
 
    axios.put(`http://localhost:8080/jobonja_war_exploded/register?name=${event.target.name.value}&family=${event.target.full_name.value}
    &userName=${event.target.user_name.value}&passWord=${event.target.password.value}&jobTitle=${event.target.job_title.value}
    &imageUrl=${event.target.image_url.value}&bio=${event.target.bio.value }`).then((response)=> {
        
        this.setState({postStatus : response.status , msg : response.data.msg ,flag :response.data.flag ,token :response.data.access_token}
         )
      },error=>{
        console.log(error)
        this.setState({postStatus : '404' , msg : "server not found"} )
      })
      
  }
  render() {
    console.log(this.state.token)
    localStorage.setItem('token',this.state.token);
    if( this.state.flag ==="0")
      alert(this.state.msg)
    return (
      this.state.flag ==="1"? 
      <Redirect to="/home" />
      :
      <div>
      <div className='top'></div>
      <div className ='middle'> 
      <div className="page-content">
      <div className="form-v6-content">
        <form className="form-detail"  onSubmit={this.registerToServer}>
          <h2>  ثبت نام در جاب اونجا </h2>
          <div className="form-row">
            <input type="text" name="name" id="name" className="input-text" placeholder="Name" required />
          </div>
          <div className="form-row">
            <input type="text" name="full_name" id="full_name" className="input-text" placeholder="Family Name" required />
          </div>
          <div className="form-row">
            <input type="text" name="user_name" id="user_name" className="input-text" placeholder="userName" required />
          </div>
          <div className="form-row">
            <input type="text" name="job_title" id="job_title" className="input-text" placeholder="Job Title" />
          </div>
          <div className="form-row">
            <input type="text" name="bio" id="bio" className="input-text" placeholder="bio" />
          </div>
          <div className="form-row">
            <input type="text" name="image_url" id="image_url" className="input-text" placeholder="image Url" />
          </div>
          <div className="form-row">
            <input type="password" name="password" id="password" className="input-text" placeholder="Password" required />
          </div>
          <div className="form-row">
            <input type="password" name="comfirm_password" id="comfirm_password" className="input-text" placeholder="Comfirm Password" required />
          </div>
     
          <div className="form-row-last">
            <input type="submit" name="register" className="register" value="تایید ثبت نام " />
          </div>
        </form>
      </div>
    </div>
    </div>
    </div>
    );
  }
}

export default withRouter(Register);