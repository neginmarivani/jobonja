import React, { Component } from 'react';

class UsersList extends Component {
  searchUser =()=>{

  }
  render() {
    return (
        <div >
          <input type="text"  name ="searchItem" onChange={this.searchUser} placeholder= "جستجو نام کاربر" />  
         
        </div>
    );
  }
}

export default UsersList;