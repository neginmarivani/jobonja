import React, { Component } from 'react';

class BidForm extends Component {
  handleSubmite =(event)=>{
    event.preventDefault()
    alert(event.target.elements.bidAmount.value)
  }
  render() {
    return (
      <div>
       <form onSubmit={this.handleSubmite}>
         <input type="text"  name ="bidAmount" placeholder="پیشنهاد خود را وارد کنید   " />

         <button type="submit" class="btn btn-primary">ثبت</button>
       </form>
       </div>
    );
  }
}
export default BidForm;