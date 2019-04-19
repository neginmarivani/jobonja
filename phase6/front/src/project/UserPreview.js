import React, { Component } from 'react';
import './UserPreview.css'
import { withRouter } from "react-router-dom";
class UserPreview extends Component {
 
  render() {
    return (
        <div >
            <div className="userPreview container">
            <div className="row justify-content-center">
            
                <div className ="userPic col-xs-3 col-sm-5 col-md-4 col-lg-2" >
                     <img  alt="title" src={this.props.user.ImageUrl} />
                </div>
                <div className = "userInfo col-xs-9 col-sm-7 col-md-8 col-lg-10">
                   <p>{this.props.user.userName +this.props.user.userFamilyName}</p> <p className="userTitle">{this.props.user.jobTitle}</p>
                     
               
                </div>
            </div>
            </div>
        </div>
    );
  }
}

export default UserPreview;