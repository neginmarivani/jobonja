
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
				<li class = "skillElem">
					HTML  <i> 10 </i>
					
				</li>
				<li  class = "skillElem">
					CSS <i > 5 </i>
				</li>
				<li class = "skillElem">
					JAVA <i > 7 </i>
				</li>
			</ul>
		
			</div>
		</div>
	</div>
    );
  }
}

export default ProjectSkills;
