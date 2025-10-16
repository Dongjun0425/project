import React, { useState, useEffect } from 'react';

function Location() {
  const [universities, setUniversities] = useState([]);

  useEffect(() => {
    // Kakao 지도 API 로드
     if (!document.querySelector(`script[src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d52daa1c41c07f80810fbc61eca944d3&autoload=false"]`)) {
      const script = document.createElement('script');
      script.src = '//dapi.kakao.com/v2/maps/sdk.js?appkey=d52daa1c41c07f80810fbc61eca944d3&autoload=false';
      script.async = true;
  
      script.onload = () => {
        window.kakao.maps.load(() => {
          fetch('') //요기만 체크!!!
            .then(response => response.json())
            .then(data => {
              const mappedUniversities = data.map(univ => ({
                name: univ.name,
                position: new kakao.maps.LatLng(univ.latitude, univ.longitude),
              }));
              setUniversities(mappedUniversities);

              const container = document.getElementById('map');
              const options = { center: new kakao.maps.LatLng(37.448868, 127.167653), level: 3 };
              const map = new kakao.maps.Map(container, options);

              const infowindows = [];

              mappedUniversities.forEach(university => {
                const marker = new kakao.maps.Marker({ position: university.position });
                marker.setMap(map);
  
                const infowindow = new kakao.maps.InfoWindow({
                  content: `<div style="padding:5px; font-size:14px;">${university.name}</div>`,
                });
                infowindows.push(infowindow);
  
                window.kakao.maps.event.addListener(marker, 'click', function () {
                  infowindows.forEach(info => info.close());
                  infowindow.open(map, marker);
                });
              });

              window.kakao.maps.event.addListener(map, 'click', function () {
                infowindows.forEach(info => info.close());
              });
            });
        });
      };
  
      document.head.appendChild(script);
    }
  }, []);

  return (
  <div style={{ position : "relative", height :"700px" }}> <div style={{ width : "520px", display : "flex", justifyContent : "flex-end" }}> <h2 style={{ paddingTop : "50px" }}>캠퍼스 위치</h2> </div>
<div id="map" style={{
  width: '600px',
  height: '500px',
  border : "1px solid #000",
  borderRadius : "20px",
  position : "absolute",
  top : "120px",
  left : "400px"}}></div>

</div>
  )
}

export default Location;