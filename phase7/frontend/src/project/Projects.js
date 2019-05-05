import React, { Component } from 'react';
import Axios from 'axios';
import { withRouter } from "react-router-dom";
import './Projects.css'
import ProjectBox from './ProjectBox'
class Projects extends Component {
  constructor(props) {
    super(props);
    this.state ={
      project :{"prerequisites":[{"name":"Java","point":1}],"imageUrl":"https://cdn.dribbble.com/users/926537/screenshots/4502924/python-2.gif","description":"امکان ساخت گروه و یا کانال به صورت گروهیامکان ارسال پیام به صورت کاربر به کاربر امکان مشاهده گروه ها، کانال ها و گفتگو های بین دونفر به همراه...","id":"cc4a31ac-648c-40d5-825b-b10163a14dde","deadline":"1970-01-11 07:00:32.848","title":"پروژه طراحی پیام رسان ","budget":3000000}
  }
}
componentDidMount=()=> {
  var intervalId = setInterval(this.getData, 100);
  // store intervalId in the state so it can be accessed later:
  this.setState({intervalId: intervalId});
}

componentWillUnmount=()=>{
  // use intervalId from the state to clear the interval
  clearInterval(this.state.intervalId);
}
  getData =async ()=>{
   
    const {
      match: { params }
    } = this.props;

   let response = await Axios.get('http://localhost:8080/project', {
    params: {
      id:params.projectId.slice(1)
    } });
    this.setState({project : response.data})
  }
  render() {
    
    return (
        <div>
          <div className = "top "></div>
	        <div className ="middle"></div>
          <ProjectBox  project={this.state.project} />
          
        </div>
        
    );
  }
}

export default withRouter(Projects);