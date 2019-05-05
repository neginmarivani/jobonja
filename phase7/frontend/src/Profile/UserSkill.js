
import React, { Component } from 'react';
import './UserSkill.css'
import axios from'axios'

class UserSkill extends Component {
    constructor(props){
        super(props);
        this.state = {
            postStatus :'404',
            msg :"",
            index :0
      
          };
      }
    EndorseCtrl =text=>()=>{
        axios.put(`http://localhost:8080/EndorseController?id=${this.props.user.id}&skillName=${text}`)
          .then((response)=> {
            this.setState({postStatus : response.status , msg : response.data.msg ,index :1})
          },error=>{
            this.setState({postStatus : '404' , msg : "server not found" , index:0} )
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
                {skill.name} <i>  {skill.point+this.state.index }   </i> </li> ) }
			</ul>
		
			</div>
		</div>
	</div>
    );
  }
}

export default UserSkill;
