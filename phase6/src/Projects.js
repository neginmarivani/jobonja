import React, { Component } from 'react';
import './Projects.css'
import Footer from './Footer'
import ProjectBox from './ProjectBox'
class Projects extends Component {
  render() {
    return (
        <div>
          <div className = "top "></div>
	        <div className ="middle"></div>
          <ProjectBox />
          
        </div>
        
    );
  }
}

export default Projects;