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
      msg :"",
      outFlag:"1"

    };
  }
  handleFormSubmit = event => {

    this.setState({flag :false});
    event.preventDefault();

    axios.put(`http://localhost:8080/jobonja_war_exploded/main/saveBidController?project=${this.props.project.id}&bidAmount=${event.target.elements.bidAmount.value}&token=${"Bearer " + localStorage.getItem('token')}`
  //   ,{
  //     headers: { authorization: "Bearer " + localStorage.getItem('token') }
  // }
  ).then((response)=> {
        this.setState({postStatus : response.status , msg : response.data.msg,outFlag :response.data.flag}
          )
      },error=>{
        console.log(error)
        this.setState({postStatus : '404' , msg : "شما قبلا پیشنهاد داده اید "} )
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
       : <Message msg={this.state.msg} status ={this.state.outFlag} />
       }
    </div>
    );
  }
}

export default ProjectBox;
