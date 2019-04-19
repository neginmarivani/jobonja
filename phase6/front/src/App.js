import './App.css';
import './Fonts.css'
import React, { Component } from 'react';
import { BrowserRouter as Router, Route} from 'react-router-dom';
import Home from './main/Home';
import Profile from './Profile/Profile';
import Projects from './project/Projects';
import Register from './Register/Register';
import Login from './Login/Login';
import Nav from './main/Nav';
import Footer from './main/Footer'
import otherUsersprofile from './Profile/OtherUserProfile'
class App extends Component {
 
  render() {
    return (
    <Router>
        <div>
          <Nav />
              <Route path='/home' component={Home} />
              <Route path='/profile' component={Profile} />
              <Route path='/project/:projectId' component={Projects} />
              <Route path='/users/:userId' component={otherUsersprofile} />
              <Route path='/login' component={Login} />
              <Route path='/register' component={Register} />
              
             
        </div>
        <Footer/>
      </Router>
      
    );
  }
}

export default App;
