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
  handleRedirect =text =>() =>{
        
    this.props.history.push("/users/:"+text)
    
}
  searchUser =(event)=>{
      console.log(event.target.value);
          if(event.target.value ==null){
             
            Axios.get(`http://localhost:8080/jobonja_war_exploded/main/users?token=${"Bearer " + localStorage.getItem('token')}`
            //   ,{
            //     headers: { authorization: "Bearer " + localStorage.getItem('token') }
            // }
            ).then((response )=> {
      
              this.setState({ usersList : response.data}); 
             
           }).catch( error =>{
               console.log(' server error ')
           });
          }
          else{
              Axios.get(`http://localhost:8080/jobonja_war_exploded/main/searchInUsersController?searchQuery=${event.target.value}&token=${"Bearer " + localStorage.getItem('token')}`
              //   ,{
              //     headers: { authorization: "Bearer " + localStorage.getItem('token') }
              // }
              ).then((response )=> {
            
                this.setState({ usersList : response.data}); 
                 
               }).catch( error =>{
                   console.log(' server error ')
               });
              }
              
  }
  componentDidMount() {
        
    Axios.get(`http://localhost:8080/jobonja_war_exploded/main/users?token=${"Bearer " + localStorage.getItem('token')}`
  //   ,{
  //     headers: { authorization: "Bearer " + localStorage.getItem('token') }
  // }
  ).then((response )=> {
      
       this.setState({ usersList : response.data}); 
      
    }).catch( error =>{
        console.log(' server error ')
    });
}
render() {

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