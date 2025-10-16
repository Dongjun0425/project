import React from 'react'
import { useState } from 'react'
import reactLogo from './assets/react.svg'
import { useNavigate } from 'react-router-dom'
import viteLogo from '/vite.svg'
import './App.css'
import './Home.jsx'
import './Signup.jsx'

function Login() {
     const navigate = useNavigate(); //페이지 이동
 
    return (
        <>
        
        
<div style = {{
    fontSize : "40px",
    marginTop : "50px",
    marginLeft : "300px",
    border : "0.5px solid rgb(179, 174, 174)",
    borderRadius : "20px",
    padding : "100px 50px",
    width : "50%",
    position: "relative",
}}>
    <p style={{
        position : "absolute",
        top: "1px", /* 텍스트를 상단으로 이동 */
        left: "350px",
        marginTop : "20px" /* 좌측 간격 설정 */
    }}>
     로그인
     </p>
     {/* <div style={{
          border : "1px solid #000000",
          width : "70%",
          height : "100%",
          marginBottom : "10px",
          marginLeft : "100px",
          marginTop : "-100px",
          borderRadius : "10px",
          backgroundColor : "#ffff00",
          border : "none"
     }}>카카오톡
     </div> */}
     <button
                style={{
                    width: "72%",
                    height: "70px",
                    border : "none",
                    borderRadius: "10px",
                    backgroundColor: "#ffff00",
                    fontSize: "30px",
                    cursor: "pointer",
                }}
               // 카카오톡으로 이동 수정필요********
            >카카오톡 로그인
            </button>
     <hr></hr>
     <div>
     <input type='text'
    style={{
        border: "1px solid #000000",
        width: "70%",
        marginBottom: "10px",
        marginLeft: "0px",
        height: "50px",
        borderRadius : "10px",
        fontSize : "20px"
    }}
    placeholder="이메일 주소"
  
/>
<input type='password'
    style={{
        border: "1px solid #000000",
        width: "70%",
        marginBottom: "10px",
        marginLeft: "0px",
        height: "50px",
        borderRadius : "10px",
        fontSize : "20px"
    }}
    placeholder="비밀번호"
/>
</div>

        <div style={{
            display : "flex",
            width : "1000px"}}>
            <div style={{
                marginLeft : "100px",
                marginTop : "-20px"
                        }}>
                <span style={{fontSize:"15px"}}>
                    <input type='checkbox'
                    style={{
                        border :"1px solid rgb(196, 188, 188)",
                        borderRadius : "30px"
                    }}/>아이디저장
                </span>
            </div>
            <div style={{ 
                marginLeft:"240px",
                marginTop : "-20px",
                textAlign : "right"}}>
                <span onClick={() =>window.open("/findpw", "_blank", "width=500,height=600")}
                    style={{ cursor : "pointer",
                        fontSize : "15px",
                        marginLeft : "80px"
                    }}>
                    비밀번호 찾기
                </span>
            </div>
            
     </div>
     <div style={{marginTop:"70px"}}>
        <p style={{fontSize : "18px", marginTop : "-30px"}}>아직 회원이 아니세요?
        <span onClick={() => navigate("/signup")}
        style={{ cursor: "pointer",
            fontSize : "15px",
            color : "#f234f0"
         }}> 회원가입</span>
        </p>
         </div> 
     </div>
     
            
        </>
)}
  
  export default Login;