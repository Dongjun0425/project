import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './App.css';
import eastSeoul from './img/eastSeoul.jpg';
import eulji from './img/eulji.jpg';
import gachon from './img/gachon.jpg';
import ict from './img/ict.jpg';
import shingu from './img/shingu.jpg';
import yongin from './img/yongin.jpg';

function All3 () {
    const navigate = useNavigate();
    const [currentIndex1, setCurrentIndex1] = useState(0);
    const Data1 = [
    { id: "Shingu", title: "신구대학교", subtitle: "성남시", imgSrc: shingu },
    { id: "yongin", title: "용인대학교", subtitle: "용인시", imgSrc: yongin },
    { id: "eastSeoul", title: "동서울대학교", subtitle: "성남시", imgSrc: eastSeoul },
    { id: "ict", title: "ICT폴리텍대학", subtitle: "광주시", imgSrc: ict },
    { id: "Shingu1", title: "신구대학교", subtitle: "성남시", imgSrc: shingu },
    { id: "yongin1", title: "용인대학교", subtitle: "용인시", imgSrc: yongin },
    { id: "eastSeoul1", title: "동서울대학교", subtitle: "성남시", imgSrc: eastSeoul },
    { id: "ict1", title: "ICT폴리텍대학", subtitle: "광주시", imgSrc: ict },
    { id: "yongin2", title: "용인대학교", subtitle: "용인시", imgSrc: yongin },
    { id: "eastSeoul2", title: "동서울대학교", subtitle: "성남시", imgSrc: eastSeoul },
    { id: "ict2", title: "ICT폴리텍대학", subtitle: "광주시", imgSrc: ict },
    { id: "ict3", title: "ICT폴리텍대학", subtitle: "광주시", imgSrc: ict },
    { id: "yongin3", title: "용인대학교", subtitle: "용인시", imgSrc: yongin },
    { id: "eastSeoul3", title: "동서울대학교", subtitle: "성남시", imgSrc: eastSeoul },
    { id: "ict3", title: "ICT폴리텍대학", subtitle: "광주시", imgSrc: ict },
  ];

    return(
<>
<div style={{
    display : "flex",
    marginLeft : "100px",
    marginTop : "50px",
    fontSize : "20px",
    fontWeight : "bold"
}}>  전문대학교
    </div>
    
                      {/* 슬라이더 */}
                      <ul style={{
                    display: "flex",
                    flexDirection: "row",
                    gap: "45px",
                    marginLeft : "50px",
                    listStyle: "none",
                    marginTop: "-20px",
                    alignItems : "center",
                    flexWrap : "wrap"
                      }}>
                        {Data1.map(item => (
                            <li
                                key={item.id}
                                style={{
                                    display: "flex",
                                    flexDirection: "column",
                                    alignItems: "center",
                                    marginTop: "30px"
                                }}
                                onMouseEnter={(e) => e.currentTarget.style.transform = "scale(1.05)"}
                                onMouseLeave={(e) => e.currentTarget.style.transform = "scale(1)"}
                                onClick={() => navigate(`${item.id}`)}
                            >
                                <div style={{
                                    backgroundColor: "rgb(244, 225, 201)",
                                    borderRadius: "20px",
                                    padding: "20px",
                                    textAlign: "center",
                                    width: "240px",
                                    boxShadow: "0px 4px 6px rgba(0, 0, 0, 0.1)"
                                }}>
                                    <img 
                                        src={item.imgSrc} 
                                        style={{
                                            width: "240px",
                                            height: "240px",
                                            borderRadius: "20px"
                                        }} 
                                    />
                                    <h2 style={{
                                        fontSize: "20px",
                                        fontWeight: "bold",
                                        margin: "0",
                                        textAlignLast: "left"
                                    }}>
                                        {item.title}
                                    </h2>
                                    <p style={{
                                        fontSize: "15px",
                                        margin: "5px 0 0 0",
                                        textAlign: "left"
                                    }}>
                                        {item.subtitle}
                                    </p>
                                </div>
                            </li>
                        ))}
                    </ul>  
            
            
</>


)

}

export default All3;