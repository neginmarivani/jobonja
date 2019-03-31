import './App.css';
import React, { Component } from 'react';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import Home from './Home';
import Profile from './Profile';
import Projects from './BidForm';
import Register from './Register';
import Login from './Login';

class App extends Component {
  render() {
    return (
    <Router>
        <div>
         
              <Route exact path='/' component={Home} />
              <Route path='/profile' component={Profile} />
              <Route path='/projects' component={Projects} />
              <Route path='/login' component={Login} />
              <Route path='/register' component={Register} />
        </div>
      </Router>
    );
  }
}

export default App;
