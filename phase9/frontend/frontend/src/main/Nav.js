import React from 'react';
import logo from '../tools/images/logo v1.png'
import { Link } from 'react-router-dom';
import './Nav.css'

  export default class Nav extends React.Component {
    render() {    
      return (
        <nav className="Nav">
          <div className="Nav__container">
            <Link to="/home" className="Nav__brand">
              <img src={logo} className="Nav__logo" alt ="logo"/>
            </Link>

            <div className="Nav__right">
              <ul className="Nav__item-wrapper">
                <li className="Nav__item">
                  <Link className="Nav__link" to="/home">Home</Link>
                </li>
                <li className="Nav__item">
                  <Link className="Nav__link" to="/profile">Profile</Link>
                </li>
                <li className="Nav__item">
                  <Link className="Nav__link" to="/login">Login</Link>
                </li>
                <li className="Nav__item">
                  <Link className="Nav__link" to="/register">Register</Link>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      );
    }
  }