import React, { Component } from 'react';
import './Message.css'

class Message extends Component {
  render() {

   {console.log(this.props.status)}
    return (
      
        <div >
              {this.props.status =="1"? 
                 <div className="success"><p><i class="fa fa-check"></i> {this.props.msg}</p> </div>
                :  <div className = "fail"><p><i class="material-icons">warning</i> {this.props.msg}</p> </div> 
                  }
                    
        </div>
    );
  }
}

export default Message;