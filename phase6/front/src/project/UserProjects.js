import React, { Component } from 'react';
import Axios from 'axios';
import { withRouter } from "react-router-dom";
import ProjectsPreview from './ProjectsPreview'
import { createHashHistory } from 'history'
import './UserProjects.css'
export const history = createHashHistory()
class UserProjects extends Component {

    
    constructor(props) {
        
        super(props);
        this.state = {
            projectsList:[],
        };
    }
    handleRedirect =text =>value =>{
        
        this.props.history.push("/project/:"+text)
        
    }
    componentDidMount() {
        
        Axios.get('http://localhost:8080/Phase-2/ProjectsController').then((response )=> {
          
           this.setState({ projectsList : response.data}); 
          
        }).catch( error =>{
            console.log(' server error ')
        });
    }
    render() {
        console.log(this.state.projectsList)
        return ( 
          <div className="projectsPrevElem" >
            <ul >
              {
                  this.state.projectsList.map((project ,i)=><li key={i} onClick={this.handleRedirect(project.id)} ><ProjectsPreview project={project}/></li> ) 
              }
            </ul>
          </div>
        );
    }
}

export default withRouter(UserProjects);