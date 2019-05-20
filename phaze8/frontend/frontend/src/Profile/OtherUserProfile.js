import React, { Component } from 'react';
import ProfileMain from './ProfileMain';
import Footer from '../main/Footer';
import Axios from 'axios'
import { withRouter } from "react-router-dom";
class OtherUserProfile extends Component {
    constructor(props){
        super(props);
        this.state = {
            pageState:"",
            user : {"skills":[{"name":"HTML","point":5},{"name":"Javascript","point":4},{"name":"C++","point":2},{"name":"Java","point":11}],"jobTitle":"پدر وب ایران","bio":"روی سنگ قبرم بنویسید او کسی بود که میخواست اسرائیل را نابود کند","id":1,"userFamilyName":"شریفی","userName":"علی"}

        };
    }
    componentDidMount=()=> {
      var intervalId = setInterval(this.getData, 100);
      // store intervalId in the state so it can be accessed later:
      this.setState({intervalId: intervalId});
    }
    
    componentWillUnmount=()=>{
      // use intervalId from the state to clear the interval
      clearInterval(this.state.intervalId);
    }
      getData =async ()=>{
       
        const {
          match: { params }
        } = this.props;
    
       let response = await Axios.get('http://localhost:8080/jobonja_war_exploded/main/UserController', {
        params: {
          id:params.userId.slice(1),
          token :"Bearer " + localStorage.getItem('token')
        } }
      //   ,{
      //     headers: { authorization: "Bearer " + localStorage.getItem('token') }
      // }
      );
        this.setState({user : response.data})
      }
  render() {
    return (
        <div>
          <ProfileMain pageState={this.state.pageState} user={this.state.user} isOtherUser={true}/>
          <Footer />
        </div>
    );
  }
}

export default withRouter(OtherUserProfile);