import React, { Component } from 'react';
import Axios from 'axios';
import './ProjectInfo.css'
class ProjectInfo extends Component {
    constructor(props){
        super(props);
        this.state ={
          deadlineReached:false ,
          msg :""
        };
      }
      
      checkDeadline =()=>{
          console.log("token in pro page ",localStorage.getItem('token'))
      //   Axios.get(`http://localhost:8080/jobonja_war_exploded/main/AuctionController?id=${this.props.project.id}&token=${"Bearer " + localStorage.getItem('token')}`
       
      //   ).then((response )=> {
  
      //     this.setState({ msg : response.data}); 
         
      //  }).catch( error =>{
      //      console.log(' server error ')
      //  });
      let response = Axios.get('http://localhost:8080/jobonja_war_exploded/main/AuctionController', {
        params: {
          id:this.props.project.id,
          token:"Bearer " + localStorage.getItem('token')
        } }
      ); 
      this.setState({msg : response.data})          
      }
      componentDidMount() {
        this.interval = setInterval(this.checkDeadline, 1000);
      }
      componentWillUnmount() {
        clearInterval(this.interval);
      }
  render() {
    return (
        <div className=" container">
            <div className="row justify-content-center">
            
                <div className ="projectPic col-xs-3 col-sm-5 col-md-4 col-lg-2" >
                    <img  alt="title" src={this.props.project.imageUrl} />
                </div>
                <div className = "infoText col-xs-9 col-sm-7 col-md-8 col-lg-10">
                
                        <h3>{this.props.project.title}</h3>
                            <p className="deadline">
                            <i className="fa fa-clock"></i>
                            زمان باقی مانده : {this.props.project.deadline} 
                            </p>
                            <p className="deadline">
                            <i class="fa fa-check"></i>
                            {this.state.msg} 
                            </p>
                            <p className="budget">
                            <i className="fa fa-money"></i> 
                            بودجه : {this.props.project.budget}
                            </p>
                            <h6>توضیحات </h6>
                            <p>{this.props.project.description}</p>
                </div>
            </div>
        </div>
    );
  }
}

export default ProjectInfo;
