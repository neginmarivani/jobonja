import React, { Component } from 'react';
import './BidForm.css'
import axios from 'axios';

class BidForm extends Component {
  state = {
    amount: 0,
  }

  handleChange = event => {
    this.setState({ amount: event.target.value });
  }

  render() {
    return (
      <div className='BidForm'>
        <h6> ثبت پیشنهاد </h6>
       <form onSubmit={this.props.onSubmit}>

       <button type="submit" class="btn btn-primary">ثبت</button>
         <input type="text"  name ="bidAmount" placeholder="پیشنهاد خود را وارد کنید   " onChange={this.handleChange}/>

         
       </form>
       </div>
    );
  }
}
export default BidForm;