import React, { Component } from 'react';
import './Message.css'

class Message extends Component {
  constructor(props){
    super(props);

  }

  render() {

    const flag =this.props.status == '200';
    return (

        <div >
              {flag ? 
                  <div className="success"><p><i class="fa fa-check"></i> {this.props.msg}</p> </div>
                : <div className = "fail"><p><i class="material-icons">warning</i> {this.props.msg}</p> </div> }
                    
        </div>
    );
  }
}

export default Message;