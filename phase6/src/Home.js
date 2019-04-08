import React, { Component } from 'react';
import UserProjects from './UserProjects'
import Userslist from './UsersList'
import './Home.css'
class Home extends Component {
  onSubmit =()=>{
    
  }
  render() {
    return (
        <div>
           <div className = "top">
              <h3>جاب اونجا خوب است !</h3>
              <p>هم‌زمان با آغاز سال نو بارش‌هایی کم سابقه مناطق وسیعی از ایران را با سیل و تبعات و خسارت‌های ناشی از آن مواجه کرد.  </p>

        <form onSubmit={this.onSubmit}>
              <input type="text"  name ="searchVar" placeholder="جستجو در جاب اونجا " />
              <button type="submit" class="btn btn-primary" >جستجو </button>
       
         </form>
            </div>
	        <div className ="middle"></div>
          <UserProjects />
          <Userslist/>
        </div>
    );
  }
}

export default Home;