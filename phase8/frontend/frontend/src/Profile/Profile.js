import React, { Component } from 'react';
import ProfileMain from './ProfileMain';
import Footer from '../main/Footer';
import Axios from 'axios'
class Profile extends Component {
  constructor(props){
    super(props);
    this.state = {
      pageState:true,
      user :""
    };
  }
 
  componentDidMount=()=>{

    setTimeout(function() { //Start the timer
      this.setState({render: true}) //After 1 second, set render to true
    }.bind(this), 1000)

    Axios.get(`http://localhost:8080/main/profile?token=${"Bearer " + localStorage.getItem('token')}`
  //   ,{
  //     headers: { authorization: "Bearer " + localStorage.getItem('token') }
  // }
  ).then((response )=> {
      
       this.setState({ user : response.data}); 
      
    }).catch( error =>{
        console.log(' server error ')
    });
}
  render() {
    
    // console.log(this.state.usersList)
    // var found =this.state.usersList.find( (single)=>{return single.id ===10})
    let renderContainer = false //By default don't render anything
    if(this.state.render) { //If this.state.render == true, which is set to true by the timer.
        renderContainer =<div>
        <ProfileMain pageState={this.state.pageState} user={this.state.user } isOtherUser={false}/>
        <Footer />
      </div>
    }
    return (
      renderContainer
    );
  }
}

export default Profile;