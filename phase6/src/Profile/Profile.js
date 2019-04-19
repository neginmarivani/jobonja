import React, { Component } from 'react';
import ProfileMain from './ProfileMain';
import Footer from '../main/Footer';
import Axios from 'axios'
class Profile extends Component {
  constructor(props){
    super(props);
    this.state = {
      pageState:true,
      usersList :[]
    };
  }

  componentDidMount=()=>{
        
    Axios.get('http://localhost:8080/Phase-2/users').then((response )=> {
      
       this.setState({ usersList : response.data}); 
      
    }).catch( error =>{
        console.log(' server error ')
    });
}
  render() {
    
    console.log(this.state.usersList)
    var found =this.state.usersList.find( (single)=>{return single.id ===1})
    return (
        <div>
          <ProfileMain pageState={this.state.pageState} user={found }/>
          <Footer />
        </div>
    );
  }
}

export default Profile;