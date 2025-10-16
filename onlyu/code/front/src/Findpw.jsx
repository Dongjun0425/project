import React from 'react'
import { useState } from 'react'
import reactLogo from './assets/react.svg'
import { useNavigate } from 'react-router-dom'
import viteLogo from '/vite.svg'
import './App.css'
import './Home.jsx'
import './Signup.jsx'
import './App.jsx'

function Findpw() {

    return (
        <>
            <div>
                <div>
                    <input type='text' style={{
                        borderRadius : "3px",
                        fontSize : "15px"
                    }}placeholder = "이메일을 입력하세요"/>
                    <input type="button" value="확인"
                    style={{
                        width : "40px"
                    }}
                    />
                    
                </div>
                
            </div>
        </>
)}
  
  export default Findpw;