import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import tailwindcss from '@tailwindcss/vite'
// https://vite.dev/config/ 
export default {
  content: ["./src/**/*.{html,js,jsx,ts,tsx}"], // src 폴더 내 모든 HTML, JS, JSX, TS, TSX 파일 스캔
  theme: {
    extend: {}, // 테마 확장 가능
  },
  plugins: [], // 추가 플러그인 등록 가능
};