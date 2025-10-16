import React from 'react'
import { useState } from 'react'
import reactLogo from './assets/react.svg'
import { useNavigate } from 'react-router-dom'
import viteLogo from '/vite.svg'
import './App.css'
import './Home.jsx'


function Ranking() {
    const navigate = useNavigate();

        const SearchBar = () => {
            const navigate = useNavigate();
        }
          
            const handleSearch = () => {
              const searchQuery = document.getElementById("searchInput").value;
              if (searchQuery) {
                // 검색 로직을 여기에 추가
                navigate(`/search?query=${searchQuery}`);
              } else {
                alert("검색어를 입력해주세요!");
              }
            };
        

    const Data = [
        { name: "신구대학교", score : 4.9 },
        { name: "동서울대학교", score : 4.0 },
        { name: "을지대학교", score : 4.7 },
        { name: "ict폴리텍대학교", score : 3.9 },
        { name: "가천대학교", score : 4.2 },
        ];

    const sortedData = Data.sort((a, b) => b.score - a.score);


    return (
        <>
        <div style={{
            height : "20px",
            marginLeft : "-400px",
            fontSize : "25px",
            paddingTop : "50px",
            fontWeight : "bold"
            
        }}>
            캠퍼스랭킹
        </div>
        <div style={{
            display : "flex",
            justifyContent :"center",
            marginTop : "-70px"
        }}>
                <ul style={{
    display: "flex",
    flexDirection: "row",
    marginLeft: "180px",
    marginTop : "-50px",
    padding: "0px",
    listStyle: "none",
    flexWrap : "wrap",
    width : "700px",
}}
> 
        <li
        style={{
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            marginTop: "150px"
        }}> 
            <div
            style={{
                padding: "20px",
                textAlign: "center",
                width: "170px",
                border: "0.5px solid rgb(191,191,191)"
            }} >
              <h2 style={{
              fontSize: "17px",
              fontWeight: "bold",
              margin: "0",
              textAlignLast : "left",
              marginBottom : "20px"
            }}>학과</h2>
                {sortedData.map((item,index) =>(
                  <div key={index}>
                <p
                style={{
                    fontSize: "13px",
                    margin: "5px 0 0 0",
                    textAlign : "left",
                    
                }}>{index +1}위 {item.name}</p>
                <p style={{
                    fontSize: "13px",
                    margin: "5px 0 0 0",
                    textAlign : "left",
                    marginBottom : "20px"
                }}>({item.score})</p>
            </div>))}
            </div>
        </li>
        <li
        style={{
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            marginTop: "150px"
        }}> 
            <div
            style={{
                padding: "20px",
                textAlign: "center",
                width: "170px",
                border: "0.5px solid rgb(191, 191, 191)"
            }} >
              <h2 style={{
              fontSize: "17px",
              fontWeight: "bold",
              margin: "0",
              textAlignLast : "left",
              marginBottom : "20px"
            }}>교수</h2>
                {sortedData.map((item,index) =>(
                  <div key={index}>
                <p
                style={{
                    fontSize: "13px",
                    margin: "5px 0 0 0",
                    textAlign : "left",
                    
                }}>{index +1}위 {item.name}</p>
                <p style={{
                    fontSize: "13px",
                    margin: "5px 0 0 0",
                    textAlign : "left",
                    marginBottom : "20px"
                }}>({item.score})</p>
            </div>))}
            </div>
        </li>
        <li
        style={{
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            marginTop: "150px"
        }}> 
            <div
            style={{
                padding: "20px",
                textAlign: "center",
                width: "170px",
                border: "0.5px solid rgb(191,191,191)"
            }} >
              <h2 style={{
              fontSize: "17px",
              fontWeight: "bold",
              margin: "0",
              textAlignLast : "left",
              marginBottom : "20px"
            }}>강의실</h2>
                {sortedData.map((item,index) =>(
                  <div key={index}>
                <p
                style={{
                    fontSize: "13px",
                    margin: "5px 0 0 0",
                    textAlign : "left",
                    
                }}>{index +1}위 {item.name}</p>
                <p style={{
                    fontSize: "13px",
                    margin: "5px 0 0 0",
                    textAlign : "left",
                    marginBottom : "20px"
                }}>({item.score})</p>
            </div>))}
            </div>
        </li>
        <li
        style={{
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
        }}> 
            <div
            style={{
                padding: "20px",
                textAlign: "center",
                width: "170px",
                border: "0.5px solid rgb(191,191,191)"
            }} >
              <h2 style={{
              fontSize: "17px",
              fontWeight: "bold",
              margin: "0",
              textAlignLast : "left",
              marginBottom : "20px"
            }}>취업률</h2>
                {sortedData.map((item,index) =>(
                  <div key={index}>
                <p
                style={{
                    fontSize: "13px",
                    margin: "5px 0 0 0",
                    textAlign : "left",
                    
                }}>{index +1}위 {item.name}</p>
                <p style={{
                    fontSize: "13px",
                    margin: "5px 0 0 0",
                    textAlign : "left",
                    marginBottom : "20px"
                }}>({item.score})</p>
            </div>))}
            </div>
        </li>
        <li
        style={{
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
        }}> 
            <div
            style={{
                padding: "20px",
                textAlign: "center",
                width: "170px",
                border: "0.5px solid rgb(191,191,191)"
            }} >
              <h2 style={{
              fontSize: "17px",
              fontWeight: "bold",
              margin: "0",
              textAlignLast : "left",
              marginBottom : "20px"
            }}>캠퍼스환경</h2>
                {sortedData.map((item,index) =>(
                  <div key={index}>
                <p
                style={{
                    fontSize: "13px",
                    margin: "5px 0 0 0",
                    textAlign : "left",
                    
                }}>{index +1}위 {item.name}</p>
                <p style={{
                    fontSize: "13px",
                    margin: "5px 0 0 0",
                    textAlign : "left",
                    marginBottom : "20px"
                }}>({item.score})</p>
            </div>))}
            </div>
        </li>
        <li
        style={{
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
        }}> 
            <div
            style={{  
                padding: "20px",
                textAlign: "center",
                width: "170px",
                border: "0.5px solid rgb(191,191,191)"
            }} >
              <h2 style={{
              fontSize: "17px",
              fontWeight: "bold",
              margin: "0",
              textAlignLast : "left",
              marginBottom : "20px"
            }}>커리큘럼</h2>
                {sortedData.map((item,index) =>(
                  <div key={index}>
                <p
                style={{
                    fontSize: "13px",
                    margin: "5px 0 0 0",
                    textAlign : "left",
                    
                }}>{index +1}위 {item.name}</p>
                <p style={{
                    fontSize: "13px",
                    margin: "5px 0 0 0",
                    textAlign : "left",
                    marginBottom : "20px"
                }}>({item.score})</p>
            </div>))}
            </div>
        </li>
        
</ul>
</div>

    </>
    )     
      
  }

  
  export default Ranking;
  