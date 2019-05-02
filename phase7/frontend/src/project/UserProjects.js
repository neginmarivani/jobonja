import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import ProjectsPreview from './ProjectsPreview'
import { createHashHistory } from 'history'
import './UserProjects.css'
export const history = createHashHistory()
class UserProjects extends Component {

   
    handleRedirect =text =>value =>{
        
        this.props.history.push("/project/:"+text)
        
    }
 
    render() {
        return ( 
          <div className="projectsPrevElem" >
            <ul >
              {
                  this.props.projectsList.map((project ,i)=><li key={i} onClick={this.handleRedirect(project.id)} ><ProjectsPreview project={project}/></li> ) 
              }
            </ul>
          </div>
        );
    }
}

export default withRouter(UserProjects);