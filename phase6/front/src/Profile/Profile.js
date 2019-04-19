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

    setTimeout(function() { //Start the timer
      this.setState({render: true}) //After 1 second, set render to true
    }.bind(this), 1000)

    Axios.get('http://localhost:8080/Phase-2/users').then((response )=> {
      
       this.setState({ usersList : response.data}); 
      
    }).catch( error =>{
        console.log(' server error ')
    });
}
  render() {
    
    console.log(this.state.usersList)
    var found =this.state.usersList.find( (single)=>{return single.id ===1})
    let renderContainer = false //By default don't render anything
    if(this.state.render) { //If this.state.render == true, which is set to true by the timer.
        renderContainer =<div>
        <ProfileMain pageState={this.state.pageState} user={found } isOtherUser={false}/>
        <Footer />
      </div>
    }
    return (
      renderContainer
    );
  }
}

export default Profile;