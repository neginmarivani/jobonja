import React, { Component } from 'react';
import './ProjectBox.css';
import ProjectInfo from './ProjectInfo';
import ProjectSkills from './ProjectSkills';
import BidForm from './BidForm';
import Message from './Message';
import axios from 'axios';

class ProjectBox extends Component {

  constructor(props){
    super(props);

    this.state ={
      flag:true,
      postStatus :'200',
      msg :""

    };
  }
  handleFormSubmit = event => {

    this.setState({flag :false});
    event.preventDefault();

    axios.put(`http://localhost:8080/saveBidController?project=${this.props.project.id}&bidAmount=${event.target.elements.bidAmount.value}`).then((response)=> {
        this.setState({postStatus : response.status , msg : response.data.msg}
          )
      },error=>{
        console.log(error)
        this.setState({postStatus : '404' , msg : "server not found"} )
      })
  }

  render() {
    console.log(this.state.msg)
    return (
    <div className = "box" >
        <ProjectInfo  project={this.props.project}/>
        <ProjectSkills project={this.props.project} />
       { (this.state.flag )
       ? <BidForm onSubmit ={this.handleFormSubmit} /> 
       : <Message msg={this.state.msg} status ={this.state.status}/>
       }
    </div>
    );
  }
}

export default ProjectBox;
