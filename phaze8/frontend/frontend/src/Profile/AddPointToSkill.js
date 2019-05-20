import React, { Component } from 'react';
// import './AddPointToSkill.css'

class AddPointToSkill extends Component {
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
        <div className="AddPointcontainer">
          {this.props.skill.map((array,index) => {
              return <button type="button" className="AddPointbtn"> <span className="AddPointbadge b2"  onClick={()=>this.handleRemoveClick(index)}>-</span> {array}</button>
          })}
        </div>
    );
  }
}

export default AddPointToSkill;