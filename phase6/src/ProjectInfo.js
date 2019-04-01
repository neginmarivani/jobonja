import React, { Component } from 'react';
import './ProjectInfo.css'
import proImg from './images/photo_2017-07-16_08-09-21.jpg'
class ProjectInfo extends Component {
  render() {
    return (
        <div class=" container">
            <div class="row justify-content-center">
            
                <div class ="projectPic col-xs-3 col-sm-5 col-md-4 col-lg-2" >
                    <img  alt="project picture" src={proImg} />
                </div>
                <div class = "infoText col-xs-9 col-sm-7 col-md-8 col-lg-10">
                
                        <h3>پروژه طراحی سایت جاب اونجا</h3>
                            <p class="deadline">
                            <i class="fa fa-clock"></i>
                            زمان باقی مانده : ۱۷ دقیقه و ۲۵ ثانیه 
                            </p>
                            <p class="budget">
                            <i class="fa fa-money"></i> 
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
