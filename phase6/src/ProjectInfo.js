import React, { Component } from 'react';
import './ProjectInfo.css'
import proImg from './images/photo_2017-07-16_08-09-21.jpg'
class ProjectInfo extends Component {
  render() {
    return (
        <div className=" container">
            <div className="row justify-content-center">
            
                <div className ="projectPic col-xs-3 col-sm-5 col-md-4 col-lg-2" >
                    <img  alt="project picture" src={proImg} />
                </div>
                <div className = "infoText col-xs-9 col-sm-7 col-md-8 col-lg-10">
                
                        <h3>پروژه طراحی سایت جاب اونجا</h3>
                            <p className="deadline">
                            <i className="fa fa-clock"></i>
                            زمان باقی مانده : ۱۷ دقیقه و ۲۵ ثانیه 
                            </p>
                            <p className="budget">
                            <i className="fa fa-money"></i> 
                            بودجه : ۲۵۰۰ تومان
                            </p>
                            <h6>توضیحات </h6>
                            <p>
                                در روزهای اخیر و پس از اظهارات صریح رییس‌جمهور مبنی بر اینکه نمی‌توان سرنوشت کشور را به تصمیم ده بیست نفر گره زد پیشنهاد برگزاری همه‌پرسی برای دو لایحه باقی مانده 
                                FATF بیشتر از گذشته مورد توجه رسانه‌ها و کارشناسان قرار گرفته است
                            </p>
                </div>
            </div>
        </div>
    );
  }
}

export default ProjectInfo;
