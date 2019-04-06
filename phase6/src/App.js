import './App.css';
import React, { Component } from 'react';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import Home from './Home';
import Profile from './Profile';
import Projects from './Projects';
import Register from './BidForm';
import Login from './Login';
import Nav from './Nav';
import Footer from './Footer'
class App extends Component {
  render() {
    return (
    <Router>
        <div>
          <Nav />
              <Route exact path='/' component={Home} />
              <Route path='/profile' component={Profile} />
              <Route path='/projects' component={Projects} />
              <Route path='/login' component={Login} />
              <Route path='/register' component={Register} />
             
        </div>
        <Footer/>
      </Router>
      
    );
  }
}

export default App;
