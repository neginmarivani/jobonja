import React, { Component } from 'react';
import './RemoveSkill.css'

class RemoveSkill extends Component {
  constructor(props){
    super(props);

    this.state ={
    };
  }

  handleRemoveClick =(e)=>{
    var index = e
      this.props.skill.splice(index, 1);
      this.forceUpdate()
  }
  render() {
    return (
        <div className="rmvSkillcontainer">
          {this.props.skill.map((array,index) => {
              return <button type="button" className="rmvSkillbtn"> <span className="badge b2"  onClick={()=>this.handleRemoveClick(index)}>-</span> {array}</button>
          })}
        </div>
    );
  }
}

export default RemoveSkill;