
import React, { Component } from 'react';
import './ProjectSkills.css'

class ProjectSkills extends Component {
  render() {
    return (
        <div class="container">
		<div class ="skills  align-items-center">
			<div  > <b> مهارت های لازم  : </b> </div>
			<div class="skill">
				<ul>
				{this.props.project.prerequisites.map((skill ,i)=><li key={i} className="skillElem" >{skill.name}  <i>{skill.point}</i></li> ) }
			</ul>
		
			</div>
		</div>
	</div>
    );
  }
}

export default ProjectSkills;
