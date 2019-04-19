import React, { Component } from 'react';
import './ProjectsPreview.css'
class ProjectsPreview extends Component {
   
   
  render() {
    console.log(this.props.project)
    return (
        <div className="projectsPreview container">
        <div className="row justify-content-center">
        
            <div className ="projectPic col-md-6 col-lg-4" >
                <img  alt="title" src={this.props.project.imageUrl} />
            </div>
            <div className = "infoText col-md-6 col-lg-8">
            
            <div className="container">
             <div className="row ">        
                    <div className="titlePrev col-xs-9 col-sm-7 col-md-8 col-lg-10" >  <h6 >{this.props.project.title}</h6> </div>
                    <div className="deadlinePrev col-xs-3 col-sm-5 col-md-4 col-lg-2 justify-content-left">  <p> زمان باقیمانده:{this.props.project.deadline} </p></div>
            </div>
            <div className="row ">  <p> {this.props.project.description}</p>  </div>
            <div className="row ">   <p className="budgetPrev">
                            بودجه : {this.props.project.budget} تومان
                </p> </div>
                <div className="row ">   
                  <div className="skillPrev">
                                <ul>
                                مهارت ها  : 
                            
                                {this.props.project.prerequisites.map((elem ,i)=> <li className = "skillElemPrev" key={i}>{ elem.name} </li> ) }
                               
                                </ul>
                                </div>
                    </div>
                    </div>
                </div>
            </div>
    </div>
    );
  }
}

export default ProjectsPreview;