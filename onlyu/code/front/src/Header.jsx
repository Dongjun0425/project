import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

function Header() {
  const navigate = useNavigate();
  const [showStickyTitle, setShowStickyTitle] = useState(false);
  const [searchQuery, setSearchQuery] = useState("");
  const [suggestions, setSuggestions] = useState([]);

  // 스크롤에 따른 sticky 제목 처리
  const handleScroll = () => {
    if (window.scrollY > 100) {
      setShowStickyTitle(true);
    } else {
      setShowStickyTitle(false);
    }
  };

  useEffect(() => {
    window.addEventListener("scroll", handleScroll);
    return () => window.removeEventListener("scroll", handleScroll);
  }, []);

  // 검색어에 따른 자동완성(제안)을 위한 API 호출 (디바운스 처리)
  useEffect(() => {
    const delayDebounceFn = setTimeout(() => {
      if (searchQuery.trim() !== "") {
        // API 엔드포인트 예시: prefix라는 쿼리로 학교명을 부분 검색
        fetch(
          `https://localhost:8080/api/universities/suggestions?prefix=${encodeURIComponent(
            searchQuery
          )}`
        )
          .then((response) => response.json())
          .then((data) => setSuggestions(data))
          .catch((error) =>
            console.error("Error fetching suggestions:", error)
          );
      } else {
        setSuggestions([]);
      }
    }, 300); // 300ms 후에 검색어 변경 반영

    return () => clearTimeout(delayDebounceFn);
  }, [searchQuery]);

  // 엔터키 입력 시 또는 검색버튼 클릭 시 처리 (검색 후 상세페이지로 이동)
  const handleSearch = () => {
    if (searchQuery.trim() === "") {
      alert("학교를 입력해주세요!");
      return;
    }

    // 정확한 학교명을 조회하는 API 호출 (검색 결과가 여러 건일 경우 첫번째 값을 사용)
    fetch(
      `https://localhost:8080/api/universities/search?name=${encodeURIComponent(
        searchQuery
      )}`
    )
      .then((response) => response.json())
      .then((data) => {
        if (data && data.length > 0) {
          const foundUniversity = data[0];
          navigate(`/name?=${foundUniversity.name}`);
        } else {
          alert("검색한 학교가 없습니다.");
        }
      })
      .catch((err) => {
        console.error("검색 중 에러 발생:", err);
        alert("검색 중 에러가 발생했습니다.");
      });
  };

  return (
    <>
      {/* 최상단 메뉴 영역 */}
      <div
        style={{
          background: "#ffffff",
          borderBottom: "1px solid #e0e0e0",
          padding: "10px 20px",
          display: "flex",
          alignItems: "center",
          justifyContent: "space-between",
          marginTop: "-40px",
        }}
      >
        <div
          style={{
            display: "flex",
            alignItems: "center",
            fontSize: "20px",
            fontWeight: "bold",
          }}
        >
          <span
            onClick={() => window.location.href = "http://localhost:5173/"}
            style={{
              cursor: "pointer",
              marginRight: "30px",
              fontSize: "30px",
            }}
          >
            학교랭킹
          </span>
          <span
            onClick={() => navigate("/ranking")}
            style={{
              cursor: "pointer",
              fontWeight: "normal",
              marginRight: "30px",
            }}
            onMouseEnter={(e) => {
              e.currentTarget.style.color = "rgb(248, 194, 127)";
              e.currentTarget.style.fontWeight = "bold";
            }}
            onMouseLeave={(e) => {
              e.currentTarget.style.color = "#000000";
              e.currentTarget.style.fontWeight = "normal";
            }}
          >
            캠퍼스 랭킹
          </span>
          <span
            onClick={() => navigate("/news")}
            style={{
              cursor: "pointer",
              fontWeight: "normal",
              marginRight: "30px",
            }}
            onMouseEnter={(e) => {
              e.currentTarget.style.color = "rgb(248, 194, 127)";
              e.currentTarget.style.fontWeight = "bold";
            }}
            onMouseLeave={(e) => {
              e.currentTarget.style.color = "#000000";
              e.currentTarget.style.fontWeight = "normal";
            }}
          >
            뉴스
          </span>
          <span
            onClick={() => navigate("location")}
            style={{ cursor: "pointer", fontWeight: "normal" }}
            onMouseEnter={(e) => {
              e.currentTarget.style.color = "rgb(248, 194, 127)";
              e.currentTarget.style.fontWeight = "bold";
            }}
            onMouseLeave={(e) => {
              e.currentTarget.style.color = "#000000";
              e.currentTarget.style.fontWeight = "normal";
            }}
          >
            캠퍼스 위치
          </span>
        </div>
        <div style={{ fontSize: "20px" }}>
          <span onClick={() => navigate("/login")} style={{ cursor: "pointer" }}>
            로그인
          </span>
          <span> | </span>
          <span onClick={() => navigate("/signup")} style={{ cursor: "pointer" }}>
            회원가입
          </span>
        </div>
      </div>

      {/* Sticky 검색 영역 */}
      <div
        style={{
          position: "sticky",
          top: 0,
          zIndex: 9,
          background: "#ffffff",
          padding: "20px",
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          borderBottom: "1px solid #ccc",
        }}
      >
        {showStickyTitle && (
          <div
            style={{
              color: "black",
              textAlign: "left",
              fontSize: "30px",
              fontWeight: "bold",
              background: "#ffffff",
              marginRight: "30px",
            }}
          >
            <span
              onClick={() => window.location.reload()}
              style={{ cursor: "pointer" }}
            >
              학교랭킹
            </span>
          </div>
        )}
        {/* 검색창 및 제안 드롭다운을 포함하는 상대 위치 컨테이너 */}
        <div style={{ position: "relative", width: "80%", maxWidth: "1000px" }}>
          <input
            id="searchInput"
            style={{
              width: "100%",
              padding: "15px",
              fontSize: "16px",
              border: "1px solid rgb(248, 194, 127)",
              borderRadius: "5px",
            }}
            type="text"
            placeholder="학교를 검색해보세요."
            value={searchQuery}
            onChange={(event) => setSearchQuery(event.target.value)}
            onKeyDown={(event) => {
              if (event.key === "Enter") {
                handleSearch();
              }
            }}
            onFocus={(event) => {
              event.target.style.borderColor = "rgb(248,194,127)";
              event.target.style.outline = "none";
            }}
          />
          {suggestions.length > 0 && (
            <div
              style={{
                position: "absolute",
                top: "100%",
                left: 0,
                width: "100%",
                background: "#fff",
                boxShadow: "0 2px 4px rgba(0,0,0,0.15)",
                zIndex: 10,
              }}
            >
              {suggestions.map((school, index) => (
                <div
                  key={index}
                  onClick={() => navigate(`/name?=${school.name}`)}
                  style={{
                    padding: "10px",
                    cursor: "pointer",
                    borderBottom: "1px solid #eee",
                  }}
                >
                  {school.name}
                </div>
              ))}
            </div>
          )}
        </div>
      </div>
    </>
  );
}

export default Header;