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

  handleSubmit = event => {
    event.preventDefault();

    alert(event.target.elements.bidAmount.value)
    const bid = {
      name: this.state.name
    };

    axios.post(`https://jsonplaceholder.typicode.com/users`, { user })
      .then(res => {
        console.log(res);
        console.log(res.data);
      })
  }

  render() {
    return (
      <div className='BidForm'>
        <h6> ثبت پیشنهاد </h6>
       <form onSubmit={this.handleSubmite}>

       <button type="submit" class="btn btn-primary">ثبت</button>
         <input type="text"  name ="bidAmount" placeholder="پیشنهاد خود را وارد کنید   " onChange={this.handleChange}/>

         
       </form>
       </div>
    );
  }
}
export default BidForm;