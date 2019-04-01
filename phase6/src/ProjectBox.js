import React, { Component } from 'react';
import './ProjectBox.css'
import ProjectInfo from './ProjectInfo'
import ProjectSkills from './ProjectSkills'
import BidForm from './BidForm';
class ProjectBox extends Component {
  render() {
    return (
    <div class = "box" >
        <ProjectInfo/>
        <ProjectSkills />
        <BidForm/>
        
    </div>
    );
  }
}

export default ProjectBox;
