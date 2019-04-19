
import React, { Component } from 'react';
import './UserSkill.css'
import axios from'axios'

class UserSkill extends Component {
    constructor(props){
        super(props);
        this.state = {
            postStatus :'404',
            msg :""
      
          };
      }
    EndorseCtrl =text=>()=>{
        axios.put(`https://localhost:8080/phase-2/EndorseController`, {
          id:this.props.user.id,
          skillName:text
         })
          .then((response)=> {
            this.setState({postStatus : response.status , msg : response.data.msg})
          },error=>{
            this.setState({postStatus : '404' , msg : "server not found"} )
          })
      }
  render() {
    return (
        <div className="container">
		<div className ="Userskills  align-items-center">
			<div className="Userskill">
				<ul>
				{this.props.user.skills.map((skill ,i)=>
                <li key={i} className="UserskillElem"onClick={this.EndorseCtrl(skill.name)} >
                {skill.name} <i>  {this.state.postStatus ==='200'?skill.point+1 :skill.point }   </i> </li> ) }
			</ul>
		
			</div>
		</div>
	</div>
    );
  }
}

export default UserSkill;
