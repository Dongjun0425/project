import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './App.css';
import './News.jsx'
import eastSeoul from './img/eastSeoul.jpg';
import eulji from './img/eulji.jpg';
import gachon from './img/gachon.jpg';
import ict from './img/ict.jpg';
import shingu from './img/shingu.jpg';
import yongin from './img/yongin.jpg';

function Home() {
  const navigate = useNavigate();
  const [currentIndex1, setCurrentIndex1] = useState(0);
  const [currentIndex2, setCurrentIndex2] = useState(0);
  const [newsData, setNewsData] = useState([]);

  const Data1 = [
    { id: "Shingu", title: "신구대학교", subtitle: "성남시", imgSrc: shingu },
    { id: "yongin", title: "용인대학교", subtitle: "용인시", imgSrc: yongin },
    { id: "eastSeoul", title: "동서울대학교", subtitle: "성남시", imgSrc: eastSeoul },
    { id: "ict", title: "ICT폴리텍대학", subtitle: "광주시", imgSrc: ict },
    { id: "Shingu1", title: "신구대학교", subtitle: "성남시", imgSrc: shingu },
    { id: "yongin1", title: "용인대학교", subtitle: "용인시", imgSrc: yongin },
    { id: "eastSeoul1", title: "동서울대학교", subtitle: "성남시", imgSrc: eastSeoul },
    { id: "ict1", title: "ICT폴리텍대학", subtitle: "광주시", imgSrc: ict },
    { id: "Shingu2", title: "신구대학교", subtitle: "성남시", imgSrc: shingu },
    { id: "yongin2", title: "용인대학교", subtitle: "용인시", imgSrc: yongin },
    { id: "eastSeoul2", title: "동서울대학교", subtitle: "성남시", imgSrc: eastSeoul },
    { id: "ict2", title: "ICT폴리텍대학", subtitle: "광주시", imgSrc: ict },
    
  ];
  const Data2 = [
    { id: "yongin", title: "용인대학교", subtitle: "용인시", imgSrc: yongin },
    { id: "eulji", title: "을지대학교", subtitle: "성남시", imgSrc: eulji },
    { id: "gachon", title: "가천대학교", subtitle: "성남시", imgSrc: gachon },
    { id: "dongch", title: "동국대학교", subtitle: "서울시", imgSrc: gachon },
    { id: "yongin1", title: "용인대학교", subtitle: "용인시", imgSrc: yongin },
    { id: "eulji1", title: "을지대학교", subtitle: "성남시", imgSrc: eulji },
    { id: "gachon1", title: "가천대학교", subtitle: "성남시", imgSrc: gachon },
    { id: "dongch1", title: "동국대학교", subtitle: "서울시", imgSrc: gachon },
    { id: "yongin2", title: "용인대학교", subtitle: "용인시", imgSrc: yongin },
    { id: "eulji2", title: "을지대학교", subtitle: "성남시", imgSrc: eulji },
    { id: "gachon2", title: "가천대학교", subtitle: "성남시", imgSrc: gachon },
    { id: "dongch2", title: "동국대학교", subtitle: "서울시", imgSrc: gachon },
  ];

  const handlePrev1 = () => {
    setCurrentIndex1((prevIndex) => Math.max(prevIndex - 3, 0));
  };

  const handleNext1 = () => {
    setCurrentIndex1((prevIndex) =>
      Math.min(prevIndex + 3, Data1.length - Math.floor(1000 / 500)) // 1000은 슬라이더 전체 너비, 260은 각 아이템 너비
    );
  };

  const handlePrev2 = () => {
    setCurrentIndex2((prevIndex) => Math.max(prevIndex - 3, 0));
  };

  const handleNext2 = () => {
    setCurrentIndex2((prevIndex) =>
      Math.min(prevIndex + 3, Data2.length - Math.floor(1000 / 500)) // 1000은 슬라이더 전체 너비, 260은 각 아이템 너비
    );
  };

  useEffect(() => {
    // 실제 API 호출 코드는 여기에 작성합니다.
    // 예시로 setTimeout을 사용하여 비동기 데이터 로딩을 흉내냅니다.
    setTimeout(() => {
      const fetchedNewsData = [
        {
          schoolName: "신구대학교",
          newsTitle: "신구대학교 장학금 지원제도",
          newsContent: "장학금 지급 제한 정학이상의 징계를\n\n 받은 학생 학생 신분에 어긋난 행동을 하였을 경우 장학",
          imageUrl: shingu // 이미지 URL 또는 import한 이미지
        },
        {
          schoolName: "용인대학교",
          newsTitle: "용인대학교 장학금 지원제도",
          newsContent: "장학금 지급 제한 정학이상의 징계를 받은 학생, 학생 신분에 어긋난 행동을 하였을 경우 장",
          imageUrl: yongin // 다른 이미지 URL
        },
      ];
      setNewsData(fetchedNewsData);
    }, 100); // 100ms 후에 데이터 설정 (실제 API 호출은 Promise 등을 사용)
  }, []);

  // newsData 상태가 업데이트될 때마다 실행되는 useEffect
  useEffect(() => {
  }, [newsData]); // <-- 의존성 배열에 newsData를 추가

    return (
        <>
        <div style={{ left : "40px", //대학교, 젅체보기
            width : "990px",
            position : "relative",
            height : "25px",
            marginLeft : "170px",
            display : "flex",
            justifyContent : "space-between",
            marginBottom : "20px",
            marginTop : "40px"
            
            }}>
                <div style={{
                    fontSize : "20px",
                    textAlign : "left",
                    fontWeight :"bold"}}>전문대학교</div>
            <div onClick={() => navigate("/all3")}
        style={{ 
            cursor: "pointer",
            textDecoration : "underline",
            textAlign : "right"}}> 전체보기</div>
           </div>{/*대학교,전체보기 끝 */}
            
        <div style={{display : "flex", //이동하는 슬라이드 박스
            justifyContent : "center",
            marginBottom : "100px"
        }}>
        <div style={{display: "flex", flexDirection: "row", alignItems: "center"}}>
            <div>
          
                <div style={{
                    width: "1000px",
                    // border: "0.5px solid #000",
                    // boxShadow : "1px 1px 1px 1px",
                    height: "400px",
                    borderRadius: "20px",
                    overflow: "hidden",
                    
                    

                }}>
                   
                   <div style={{
  background: 'linear-gradient(to right, rgba(0, 0, 0, 0.5), transparent)', /* 왼쪽 그라데이션 */
  marginTop : "10px",
  width: 'auto',
  height: '345px',
  position : "absolute" ,
  zIndex :"1",
  visibility: currentIndex1 > 0 ? "visible" : "hidden" // <--- 수정됨: 첫 번째 요소가 아니면 보임
}}>
  <button
    style={{
      backgroundColor: "rgba(0, 0, 0, 0.5)",
      visibility: currentIndex1 > 0 ? "visible" : "hidden", // <--- 수정됨: 첫 번째 요소가 아니면 보임
      color: "white",
      border: "none",
      borderRadius: "20%",
      width: "50px",
      position : "absoulte",
      height: "50px",
      cursor: "pointer",
      marginTop : "160px",
      fontSize: "20px",
      marginRight: "10px"
    }}
    onClick={handlePrev1}
  >
    {"<"}
  </button>
</div>

<div style={{
  background: 'linear-gradient(to left, rgba(0, 0, 0, 0.2), transparent)', /* 오른쪽 그라데이션 */
  marginTop : "10px",
  width: 'auto',
  height: '345px',
  position : "absolute",
  zIndex :"1",
  right :"210px",
  visibility: currentIndex1 < Data1.length - Math.floor(1000 / 400) ? "visible" : "hidden" // <--- 수정됨: 마지막 요소가 아니면 보임
}}>
  {/* 다음 버튼 */}
  <button
    style={{
      backgroundColor: "rgba(0, 0, 0, 0.5)",
      visibility: currentIndex1 < Data1.length - Math.floor(1000 / 400) ? "visible" : "hidden", // <--- 수정됨: 마지막 요소가 아니면 보임
      color: "white",
      border: "none",
      borderRadius: "20%",
      width: "50px",
      height: "50px",
      marginTop : "160px",
      cursor: "pointer",
      fontSize : "20px",
      zIndex : "1"
    }}
    onClick={handleNext1}
  >
    {">"}
  </button>
</div>
                    
                    {/* 슬라이더 */}
                    <ul style={{
  display: "flex",
  flexDirection: "row",
  gap: "45px",
  padding: "0px",
  listStyle: "none",
  marginTop: "-20px",
  alignItems : "center",
  transition: "transform 0.3s ease-in-out",
  transform: `translateX(-${currentIndex1 * 290}px)`, // 슬라이드 이동 계산
  backgroundImage: (() => {
    const visibleItemCount = Math.floor(1000 / 290); // 현재 보이는 아이템 개수 (대략 3개)
    const lastVisibleIndex = currentIndex1 + visibleItemCount - 1;
    const isFirst = currentIndex1 === 0;
    const isLast = lastVisibleIndex >= Data1.length - 1;
    const hasPrev = currentIndex1 > 0;
    const hasNext = lastVisibleIndex < Data1.length - 1;
    const leftGradient = hasPrev ? 'linear-gradient(to left, rgba(255, 255, 255, 0.3), transparent)' : 'none';
    const rightGradient = hasNext ? 'linear-gradient(to right, rgba(255, 255, 255, 0.3), transparent)' : 'none'; 
    const combinedStyle = {
      background: `${leftGradient}, ${rightGradient}`, backgroundSize: 'cover', backgroundRepeat: 'no-repeat',};

    if (isFirst && isLast) {
      return 'none'; // 처음이자 마지막 요소인 경우
    } else if (isFirst) {
      return rightGradient; // 처음 요소인 경우 오른쪽 그라데이션만
    } else if (isLast) {
      return leftGradient; // 마지막 요소인 경우 왼쪽 그라데이션만
    } else {
      return `linear-gradient(to left, rgba(255, 255, 255, 0.3), transparent), linear-gradient(to right, rgba(255, 255, 255, 0.3), transparent)`; // 중간 요소들
    }
  })(),
  backgroundPosition: 'left, right',
  backgroundRepeat: 'no-repeat, no-repeat',
  backgroundSize: '50px auto, 50px auto'
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
                    

                   
                </div>

            </div>

            </div>
            </div>{/* 슬라이드 박스 끝  */}

            {/*2번쪠 요소~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`*/}

            <div style={{ left : "40px", //대학교, 젅체보기
            width : "990px",
            position : "relative",
            height : "25px",
            marginLeft : "170px",
            display : "flex",
            justifyContent : "space-between",
            marginBottom : "20px"
            
            }}>
                <div style={{
                    fontSize : "20px",
                    textAlign : "left",
                    fontWeight :"bold"}}>4년제대학교</div>
            <div onClick={() => navigate("/all4")}
        style={{ 
            cursor: "pointer",
            textDecoration : "underline",
            textAlign : "right"}}> 전체보기</div>
           </div>{/*대학교,전체보기 끝 */}
            
        <div style={{display : "flex", //이동하는 슬라이드 박스
            justifyContent : "center",
            marginBottom : "40px"
        }}>
        <div style={{display: "flex", flexDirection: "row", alignItems: "center"}}>
           

            <div>
          
            <div style={{
  width: "1000px",
  height: "400px",
  borderRadius: "20px",
  overflow: "hidden",
  position:"relative",
  backgroundImage: (() => {
    const visibleItemCount = Math.floor(1000 / 290);
    const lastVisibleIndex = currentIndex2 + visibleItemCount - 1;
    const isFirst = currentIndex2 === 0;
    const isLast = lastVisibleIndex >= Data2.length - 1;
    const hasPrev = currentIndex2 > 0;
    const hasNext = lastVisibleIndex < Data2.length - 1;
    const leftGradient = hasPrev ? 'linear-gradient(to left, rgba(255, 255, 255, 0.3), transparent)' : 'none';
    const rightGradient = hasNext ? 'linear-gradient(to right, rgba(255, 255, 255, 0.3), transparent)' : 'none';

    if (isFirst && isLast) {
      return 'none';
    } else if (isFirst) {
      return rightGradient;
    } else if (isLast) {
      return leftGradient;
    } else {
      return `linear-gradient(to left, rgba(255, 255, 255, 0.3), transparent), linear-gradient(to right, rgba(255, 255, 255, 0.3), transparent)`;
    }
  })(),
  backgroundPosition: 'left, right',
  backgroundRepeat: 'no-repeat, no-repeat',
  backgroundSize: '50px auto, 50px auto'
}}>
                   
                   <div style={{
  background: 'linear-gradient(to right, rgba(0, 0, 0, 0.5), transparent)', /* 왼쪽 그라데이션 */
  marginTop : "10px",
  width: 'auto',
  height: '345px',
  position : "absolute" ,
  zIndex :"1",
  left :"0",
  visibility: currentIndex2 > 0 ? "visible" : "hidden" // <--- 수정됨: 첫 번째 요소가 아니면 보임
}}>
  <button
    style={{
      backgroundColor: "rgba(0, 0, 0, 0.5)",
      visibility: currentIndex2 > 0 ? "visible" : "hidden", // <--- 수정됨: 첫 번째 요소가 아니면 보임
      color: "white",
      border: "none",
      borderRadius: "20%",
      width: "50px",
      position : "absoulte",
      height: "50px",
      cursor: "pointer",
      marginTop : "160px",
      fontSize: "20px",
      marginRight: "10px"
    }}
    onClick={handlePrev2}
  >
    {"<"}
  </button>
</div>

<div style={{
  background: 'linear-gradient(to left, rgba(0, 0, 0, 0.2), transparent)', /* 오른쪽 그라데이션 */
  marginTop : "10px",
  width: 'auto',
  height: '345px',
  position : "absolute",
  zIndex :"1",
  right :"0px",
  visibility: currentIndex2 < Data2.length - Math.floor(1000 / 400) ? "visible" : "hidden" // <--- 수정됨: 마지막 요소가 아니면 보임
}}>
  {/* 다음 버튼 */}
  <button
    style={{
      backgroundColor: "rgba(0, 0, 0, 0.5)",
      visibility: currentIndex2 < Data2.length - Math.floor(1000 / 400) ? "visible" : "hidden", // <--- 수정됨: 마지막 요소가 아니면 보임
      color: "white",
      border: "none",
      borderRadius: "20%",
      width: "50px",
      height: "50px",
      marginTop : "160px",
      cursor: "pointer",
      fontSize : "20px",
      zIndex : "1"
    }}
    onClick={handleNext2}
  >
    {">"}
  </button>
</div>
                    
                    {/* 슬라이더 */}
                    <ul style={{
    display: "flex",
    flexDirection: "row",
    gap: "45px",
    padding: "0px",
    listStyle: "none",
    marginTop: "-20px",
    alignItems: "center",
    transition: "transform 0.3s ease-in-out",
    transform: `translateX(-${currentIndex2 * 290}px)`,

  }}>

                        {Data2.map(item => (
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
                                    backgroundColor: "rgb(244,225,201)",
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
                    

                   
                </div>

            </div>

            </div>
            </div>{/* 슬라이드 박스 끝  */}
            {/* ------------------------------------------------뉴스----------------------------------------- */}
            <div style={{ left : "40px", //대학교, 젅체보기
            width : "990px",
            position : "relative",
            height : "25px",
            marginLeft : "170px",
            display : "flex",
            justifyContent : "space-between",
            marginBottom : "40px"
            
            }}>
                <div style={{
                    fontSize : "20px",
                    textAlign : "left",
                    fontWeight :"bold"}}>최신뉴스</div>
           </div>
            <div> 
        {newsData.map((data, index) => (
          <div key={index} style={{ 
            display: 'flex', 
            alignItems: 'flex-start', 
            width: '100px',
            marginLeft : "200px",
            marginBottom : "40px"
             }}>
            <div style={{
              display: "flex",
              justifyContent: "flex-end",
              width: "400px"
            }}>
              <img
                src={data.imageUrl}
                style={{
                  width: "200px",
                  height: "160px",
                  borderRadius: "20px",
                  justifyContent: "right",
                  cursor : "pointer",
                  marginBottom : "0px"
                }}
                alt={data.schoolName}
                onClick={() => navigate(data.schoolName)}
              />
            </div>
            <div style={{
              display: "flex",
              width: "1000px",
              flexDirection : "column",
              textAlign : "left",

            }}>
              <p style={{
                marginLeft: "30px",
                fontSize: "30px",
                fontWeight: "bold",
                width: "700px",
                textAlign: "left",
                cursor : "pointer",
                marginBottom : "10px"
              }} onClick={() => navigate(data.schoolName)}>
                {data.newsTitle}
                <p style={{
                  fontSize: "15px",
                  fontWeight: "normal",
                  whiteSpace: "pre-line",
                  cursor : "pointer",
                  marginTop  : "20px",
                  lineHeight : "15px"
                }} onClick={() => navigate(data.schoolName)}>
                  {data.newsContent}
                </p>
              </p>
            </div>
          </div>
        ))}
      </div>
    </>
  );
};
export default Home;