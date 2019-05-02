import React, { Component } from 'react';
import UserProjects from '../project/UserProjects'
import Userslist from '../project/UsersList'
import Axios from 'axios';
import './Home.css'
class Home extends Component {
  constructor(props){
    super(props);
    this.state = {
      searchProjectQuery:"",
      projectsList:[]
  };
  }
  componentWillMount =()=>{
    Axios.get('http://localhost:8080/Phase-2/ProjectsController').then((response )=> {
          
      this.setState({ projectsList : response.data}); 
     
   }).catch( error =>{
       console.log(' server error ')
   });
  }
  onSubmit =(event)=>{
    console.log(event.target.value);
        if(event.target.value ==null){
           
            Axios.get('http://localhost:8080/Phase-2/ProjectsController').then((response )=> {
          
                this.setState({ projectsList : response.data}); 
               
             }).catch( error =>{
                 console.log(' server error ')
             });
        }
        else{
            Axios.get(`http://localhost:8080/Phase-2/SearchInProjectsController?searchQuery=${event.target.value}`).then((response )=> {
          
                this.setState({ projectsList : response.data}); 
               
             }).catch( error =>{
                 console.log(' server error ')
             });
            }
  }
  render() {
    return (
        <div>
           <div className = "topHome">
              <h3>جاب اونجا خوب است !</h3>
              <p>هم‌زمان با آغاز سال نو بارش‌هایی کم سابقه مناطق وسیعی از ایران را با سیل و تبعات و خسارت‌های ناشی از آن مواجه کرد.  </p>

        <form >
              <input type="text"  name ="searchVar" placeholder="جستجو در جاب اونجا " onChange={this.onSubmit} />
              <button type="submit" className="btn btn-primary" >جستجو </button>
         </form>
            </div>
	        <div className ="middle">
          <div className=" container">
            <div className="row justify-content-center">
            
                <div className =" col-xs-4 col-sm-6 col-md-5 col-lg-4" >
                   <Userslist/>
                </div>
                <div className = "col-xs-8 col-sm-6 col-md-7 col-lg-8">
                   <UserProjects projectsList={this.state.projectsList} />
                </div>
            </div>
          </div>
          </div>
          
        </div>
    );
  }
}

export default Home;