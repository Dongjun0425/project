import React, { useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, useLocation } from 'react-router-dom';
import EmotionService from './services/EmotionService';
import Home from './Home';
import Signup from './Signup';
import Login from './Login';
import AnnouncementDetail from './AnnouncementDetail';
import Location from './Location';
import News from './News';
import Ranking from './Ranking';
import Footer from './footer';
import Header from './Header';
import All3 from './All3';
import All4 from './All4';
import KakaoLogin from './KakaoLogin';
import Findpw from './findpw';
import NewsInput from './NewsInput';

function Layout() {
  const location = useLocation();
  const hideLayout = location.pathname === "/findpw";

  return (
    <>
      {!hideLayout && <Header />}
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/login" element={<Login />} />
        <Route path="/Location" element={<Location />} />
        <Route path="/News" element={<News />} />
        <Route path="/Ranking" element={<Ranking />} />
        <Route path="/All3" element={<All3 />} />
        <Route path="/All4" element={<All4 />} />
        <Route path="/KakaoLogin" element={<KakaoLogin />} />
        <Route path="Announcement/:id" element={<AnnouncementDetail />} />
        <Route path="NewsInput" element={<NewsInput />} />
        <Route path="/Footer" element={<Footer />} />
        <Route path="/findpw" element={<Findpw />} />
      </Routes>
      {!hideLayout && <Footer />}
    </>
  );
}

function App() {
  useEffect(() => {
    EmotionService.getEmotions()
      .then(response => console.log(response.data))
      .catch(error => console.error(error));
  }, []);

  return (
    <Router>
      <Layout />
    </Router>
  );
}

export default App;
