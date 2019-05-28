import React, { Component } from 'react';
import './ChooseSkill.css';
import axios from 'axios';

class ChooseSkill extends Component {
    constructor(props){
        super(props);
    
        this.state ={
            myData :[]
        };
      }
      componentDidMount= () => {
        axios.get(`http://142.93.134.194:8000/joboonja/skill`)
          .then((response)=> {
              
            this.setState({myData: response.data})
          },error=>{
            console.log("cant get skills from server");
          })
      }
    
  render() {
    return (
        <div className="chooseSkillskill"> 
            <div className="chooseSkilllabel l1">مهارت ها:</div>
                <div className="chooseSkilllabel l2">
                    <select className="chooseSkillform-control" onChange={this.props.onSubmit} >
                    <option>--انتخاب مهارت --</option>
                    {this.state.myData.map((elem ,i) => <option key={i} value={elem.name}>{elem.name}</option>) }             
                    </select>
                    <button type="submit" className="chooseSkilllabel l3" > مهارت افزودن </button>

                </div>
        </div>
    );
  }
}

export default ChooseSkill;
