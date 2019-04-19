import React, { Component } from 'react';
import ChooseSkill  from './ChooseSkill';
import './ProfileMain.css';
import { withRouter } from "react-router-dom";
import axios from 'axios';
import RemoveSkill from './RemoveSkill';
import AddPointToSkill from './AddPointToSkill';


class ProfileMain extends Component {

  constructor(props){
    super(props);
    this.state = {
        skill:[],
        postStatus :'200',
        msg :""
  
      };
  }
  componentDidMount =()=>{
    setTimeout(function() { //Start the timer
      this.setState({render: true}) //After 1 second, set render to true
    }.bind(this), 1000)
  }
  handleFormSubmit = (event) => {
    event.preventDefault();
    // const amount = event.target.elements.bidAmount.value;
  
    axios.post(`https://localhost:8080/phase5/AddSkillController`, { nameOfwantedSkill:event.target.value })
      .then((response)=> {
        this.setState({postStatus : response.status , msg : response.data.msg})
      },error=>{
        this.setState({postStatus : '404' , msg : "server not found"} )
      })
    var joined = this.state.skill.concat(event.target.value);
    this.setState({ skill: joined })
  }

  render() {
    let renderContainer = false //By default don't render anything
    if(this.state.render) { //If this.state.render == true, which is set to true by the timer.
        renderContainer = <div className="main" > 
        <div className="top" > <h1> <br/> </h1></div>
       <div className="whole">
           <div className="pic">
                  <img  alt="title" src={this.props.user.ImageUrl} />
           </div>
           <div className="topic">

                   <h1> نام و نام خانوادگی </h1>
                   <h2> {this.props.user.userName}{this.props.user.userFamilyName} </h2>
           </div>
           <div className="text"> 
               <p> {this.props.user.bio}</p>
           </div>
{console.log(this.props.pageState)}
           {this.props.pageState ?<ChooseSkill onSubmit ={this.handleFormSubmit}/>:null}
           {this.props.pageState ? <RemoveSkill skill={this.state.skill}/>:null}
           {!this.props.pageState ? <AddPointToSkill skill={this.state.skill}/>:null}
       </div>
   </div>
    }
    return (
      renderContainer //Render the dom elements, or, when this.state == false, nothing.
    )
  }
}

export default withRouter(ProfileMain);