import React, { Component } from 'react';
import './Register.css'
import '../tools/css/demo.css'
import '../tools/css/normalize.css'
class Register extends Component {
  render() {
    return (
      <div>
      <div className='top'></div>
      <div className ='middle'> 
      <div class="page-content">
      <div class="form-v6-content">
        <form class="form-detail" action="#" method="post">
          <h2>  ثبت نام در جاب اونجا </h2>
          <div class="form-row">
            <input type="text" name="name" id="name" class="input-text" placeholder="Name" required />
          </div>
          <div class="form-row">
            <input type="text" name="full-name" id="full-name" class="input-text" placeholder="Family Name" required />
          </div>
          <div class="form-row">
            <input type="text" name="your-email" id="your-email" class="input-text" placeholder="Job Title" required pattern="[^@]+@[^@]+.[a-zA-Z]{2,6}"/>
          </div>
          <div class="form-row">
            <input type="text" name="bio" id="bio" class="input-text" placeholder="bio" />
          </div>
          <div class="form-row">
            <input type="url" name="imageurl" id="imageurl" class="input-text" placeholder="Image Url" />
          <div class="form-row">
            <input type="password" name="password" id="password" class="input-text" placeholder="Password" required />
          </div>
          <div class="form-row">
            <input type="password" name="comfirm-password" id="comfirm-password" class="input-text" placeholder="Comfirm Password" required />
          </div>
          </div>
          <div class="form-row-last">
            <input type="submit" name="register" class="register" value="تایید ثبت نام "/>
          </div>
        </form>
      </div>
    </div>
    </div>
    </div>
    );
  }
}

export default Register;