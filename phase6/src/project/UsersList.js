import React, { Component } from 'react';
import Axios from 'axios';
import UserPreview from './UserPreview'
import { withRouter } from "react-router-dom";
import { createHashHistory } from 'history'
import './UserList.css'

export const history = createHashHistory()
class UsersList extends Component {
  constructor(props){
    super(props);
    this.state ={
      usersList:[]
    };
  }
  handleRedirect =text =>value =>{
        
    this.props.history.push("/users/:"+text)
    
}
  searchUser =()=>{

  }
  componentDidMount() {
        
    Axios.get('http://localhost:8080/Phase-2/users').then((response )=> {
      
       this.setState({ usersList : response.data}); 
      
    }).catch( error =>{
        console.log(' server error ')
    });
}
render() {
  console.log(this.state.usersList)
    return ( 
      
      <div className="userPrevElem" >
        <ul >
          <li>
        <input type="text"  name ="searchItem" onChange={this.searchUser} placeholder= "جستجو نام کاربر" /> 
        </li>
          {
              this.state.usersList.map((user ,i)=>
               <li key={i} onClick={this.handleRedirect(user.id)}><UserPreview user={user}/>
                </li> ) 
          }
        </ul>
      </div>
    );
}
}

export default withRouter(UsersList);