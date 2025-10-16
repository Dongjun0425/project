import React from 'react'
import { useState } from 'react'
import reactLogo from './assets/react.svg'
import { useNavigate } from 'react-router-dom'
import viteLogo from '/vite.svg'
import './App.css'


function Footer() {
    return (
        <div
        style={{ 
        position: "absolute",
        display : "flex",          /* 하단에 위치 */
        left: "0",               /* 왼쪽 끝에 고정 */
        width: "100%",           /* 화면 전체 너비 */
        height: "130px",           /* 푸터 높이 */
        backgroundColor: "#ECECEC", /* 배경 색상 */
        margin: "0",             /* 여백 제거 */
        padding: "0",
        textAlign : "center"            /* 패딩 제거 */
    }}
>
    <p style={{
        textAlign : "left",
        fontWeight : "bold",
        marginLeft : "250px",
        marginTop : "10px",
        fontSize : "15px",
        color : "#009900"
    }}>
(주) 최관우컴퍼니 <br></br> 
</p>
<p style={{
    textAlign : "left",
    marginTop: "12px",
    marginLeft : "-120px",
    color : "#00000",
    lineHeight : "1.7",
    fontSize : "13px"}}>
<br></br>경기도 성남시 중원구 광명로 377 남관 111호 &nbsp; |&nbsp; 조원 : 최관우, 전동준, 박민기, 이승재, 이하석
<br></br>사업자등록번호   123-12-12345 통신판매업 <br></br>유료 직업소개사업 등록번호 987-654-12335 <br></br>
대표번호 031-740-1114 (09:00~18:00 / 주말 및 공휴일 휴무) &nbsp; |&nbsp; 대표이메일 : ehd7279@g.shingu.ac.kr

</p>
</div>

)}
export default Footer;