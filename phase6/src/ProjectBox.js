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
    const amount = event.target.elements.bidAmount.value;
  
    axios.post(`https://localhost:8080/phase5/saveBidController`, { project:this.props.projectId ,bidAmount :amount })
      .then((response)=> {
        this.setState({postStatus : response.status , msg : response.data.msg})
      },error=>{
        this.setState({postStatus : '404' , msg : "server not found"} )
      })
  }

  render() {
    return (
    <div className = "box" >
        <ProjectInfo/>
        <ProjectSkills />
       { (this.state.flag )
       ? <BidForm onSubmit ={this.handleFormSubmit} /> 
       : <Message msg={this.state.msg} status ={this.state.status}/>
       }
    </div>
    );
  }
}

export default ProjectBox;
