import React from 'react'
import { useState } from 'react'
import reactLogo from './assets/react.svg'
import { useNavigate } from 'react-router-dom'
import viteLogo from '/vite.svg'
import './App.css'
import './Home.jsx'

function Signup() {
     const navigate = useNavigate(); //페이지 이동
     const [email, setEmail] = useState(""); // 이메일 상태
     const [password, setPassword] = useState(""); // 비밀번호 상태
     const [confirmPassword, setConfirmPassword] = useState(""); // 비밀번호 확인 상태

     const handleSubmit = () => {
        
        // 비밀번호 확인 로직 추가
        if (password !== confirmPassword) {
            alert("비밀번호가 일치하지 않습니다.");
            return;
        }
        else if(!(email.indexOf('@')>1) ||!(email.split('@')[1].indexOf('.')>1)) {
             alert("잘못된 이메일 양식입니다.");
        }
        else {
        alert("회원가입이 완료됐습니다.");
    } }


 
    return (
        <>
        
        
<div style = {{
    fontSize : "40px",
    marginTop : "50px",
    marginLeft : "300px",
    border : "0.5px solid rgb(179, 174, 174)",
    borderRadius : "20px",
    padding : "150px 50px",
    width : "50%",
    position: "relative",
}}>
    <p style={{
        position : "absolute",
        top: "1px", /* 텍스트를 상단으로 이동 */
        left: "330px", /* 좌측 간격 설정 */
    }}>
     회원가입
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
                    height: "50px",
                    border : "none",
                    borderRadius: "10px",
                    backgroundColor: "#ffff00",
                    fontSize: "30px",
                    cursor: "pointer",
                }}
                onClick={() => navigate('/kakaoLogin')} // 카카오톡으로 이동 수정필요********
            >카카오톡
            </button>
     <hr></hr>  
     <div
  style={{
    display: "flex",
    alignItems: "center",
    width: "88%",
    marginBottom: "20px",
    marginLeft : "100px"
  }}
>
  <input
    type="text"
    style={{
      border: "1px solid #000000",
      width: "80%", 
      height: "50px",
      borderRadius: "10px",
      fontSize: "20px",
      marginRight: "10px"
    }}
    placeholder="이메일 주소"
    value={email}
    onChange={(e) => setEmail(e.target.value)}
  />
  <input
    type="button"
    value="중복확인"
    style={{
      border: "1px solid #000000",
      width: "80px",
      height: "40px",
      borderRadius: "10px",
      fontSize: "16px",
      cursor: "pointer"
    }}
  />
</div>
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
    value={password} // 상태 연결
    onChange={(e) => setPassword(e.target.value)} // 상태 업데이트
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
    placeholder="비밀번호 확인"
    value={confirmPassword} // 상태 연결
    onChange={(e) => setConfirmPassword(e.target.value)} // 상태 업데이트
/>
<hr></hr>
<button
                style={{
                    width: "72%",
                    height: "50px",
                    border : "none",
                    borderRadius: "10px",
                    backgroundColor: "#4CAF50",
                    color: "white",
                    fontSize: "30px",
                    cursor: "pointer",
                }}
                onClick={handleSubmit} // 버튼 클릭 이벤트 핸들러(비빌번호 확인 후 이동로직 확인)******
            >
                가입하기
            </button>

     </div>
            
        </>
)}
  
  export default Signup;