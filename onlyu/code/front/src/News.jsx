import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function News() {
    const navigate = useNavigate();

    // 카드형 뉴스 데이터
    const allAnnouncements = [
        {
            id: 1,
            title: "신구",
            content: "Shingu",
            image: "https://i.namu.wiki/i/OgX-utR2ZRxXOyovCSif6gvNJoYh6MdH1JbzYraW1u7C502He83jDsKrKPka2-KF9pcoZQg3v31DNqsjELdcBw.svg",
            category: "학교"
        },
        {
            id: 2,
            title: "참치앤김치",
            content: "참치에는 와인!",
            image: "https://flexible.img.hani.co.kr/flexible/normal/900/675/imgdb/original/2021/0825/20210825503877.jpg",
            category: "음식"
        },
        {
            id: 3,
            title: "오늘의 교보문고",
            content: "BEST 추천 책",
            image: "https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9791191114768.jpg",
            category: "도서"
        },
    ];

    const categories = ['전체', '학교', '음식', '도서'];
    const [selectedCategory, setSelectedCategory] = useState('전체');
    const [searchText, setSearchText] = useState('');
    const [currentPage, setCurrentPage] = useState(1);
    const itemsPerPage = 6;

    const filteredAnnouncements = allAnnouncements.filter(item =>
        (selectedCategory === '전체' || item.category === selectedCategory) &&
        (item.title.includes(searchText) || item.content.includes(searchText))
    );

    const paginated = filteredAnnouncements.slice(
        (currentPage - 1) * itemsPerPage,
        currentPage * itemsPerPage
    );

    const totalPages = Math.ceil(filteredAnnouncements.length / itemsPerPage);

    const handleAnnouncementClick = (announcement) => {
        navigate(`/announcement/${announcement.id}`, { state: announcement });
    };

    const handlePostClick = (post) => {
        navigate(`/post/${post.id}`, { state: post });
    };

    return (
        <div style={{ maxWidth: "1200px", margin: "0 auto", padding: "60px 20px", background: "#fff" }}>
            {/* 제목 */}
            <h1 style={{ fontSize: "40px", fontWeight: "bold", textAlign: "center" }}>뉴스</h1>
            <p style={{ fontSize: "18px", textAlign: "center", marginBottom: "30px", color: "#666" }}>
                아래 뉴스를 클릭하여 자세한 내용을 확인하세요.
            </p>

            {/* 카테고리 탭 */}
            <div style={{ display: 'flex', justifyContent: 'center', marginBottom: '20px', gap: '10px', flexWrap: 'wrap' }}>
                {categories.map(category => (
                    <button
                        key={category}
                        onClick={() => {
                            setSelectedCategory(category);
                            setCurrentPage(1);
                        }}
                        style={{
                            padding: '8px 16px',
                            border: '1px solid #ccc',
                            borderRadius: '20px',
                            background: selectedCategory === category ? '#0073e9' : '#f5f5f5',
                            color: selectedCategory === category ? '#fff' : '#333',
                            cursor: 'pointer'
                        }}
                    >
                        {category}
                    </button>
                ))}
            </div>

            {/* 카드 리스트 */}
            <div style={{
                display: "flex",
                flexWrap: "wrap",
                justifyContent: "center",
                gap: "20px",
                paddingBottom: "40px"
            }}>
                {paginated.map((announcement) => (
                    <div
                        key={announcement.id}
                        style={{
                            width: "300px",
                            border: "1px solid #ddd",
                            borderRadius: "10px",
                            overflow: "hidden",
                            boxShadow: "0 4px 6px rgba(0, 0, 0, 0.1)",
                            cursor: "pointer",
                            backgroundColor: "#fff",
                            transition: "transform 0.3s, box-shadow 0.3s"
                        }}
                        onClick={() => handleAnnouncementClick(announcement)}
                        onMouseEnter={(e) => {
                            e.currentTarget.style.transform = "scale(1.05)";
                            e.currentTarget.style.boxShadow = "0 6px 10px rgba(0, 0, 0, 0.15)";
                        }}
                        onMouseLeave={(e) => {
                            e.currentTarget.style.transform = "scale(1)";
                            e.currentTarget.style.boxShadow = "0 4px 6px rgba(0, 0, 0, 0.1)";
                        }}
                    >
                        <img
                            src={announcement.image}
                            alt={announcement.title}
                            style={{
                                width: "100%",
                                height: "200px",
                                objectFit: "cover",
                                borderBottom: "1px solid #ddd"
                            }}
                        />
                        <div style={{ padding: "15px" }}>
                            <h3 style={{ fontSize: "20px", fontWeight: "bold", marginBottom: "10px" }}>{announcement.title}</h3>
                            <p style={{ fontSize: "14px", color: "#777" }}>
                                {announcement.content.length > 30
                                    ? announcement.content.substring(0, 30) + "..."
                                    : announcement.content}
                            </p>
                        </div>
                    </div>
                ))}
            </div>

            {/* 페이지네이션 */}
            <div style={{ textAlign: "center", marginBottom: "60px" }}>
                {Array.from({ length: totalPages }, (_, i) => (
                    <button
                        key={i + 1}
                        onClick={() => setCurrentPage(i + 1)}
                        style={{
                            margin: '0 5px',
                            padding: '8px 12px',
                            background: currentPage === i + 1 ? '#0073e9' : '#fff',
                            color: currentPage === i + 1 ? '#fff' : '#333',
                            border: '1px solid #ccc',
                            borderRadius: '4px',
                            cursor: 'pointer'
                        }}
                    >
                        {i + 1}
                    </button>
                ))}
            </div>

            {/* 글쓰기 버튼 (카드형 뉴스 목록 맨 아래 위치) */}
            <div style={{ textAlign: "right", marginBottom: "60px" }}>
                <button
                    onClick={() => navigate('/NewsInput')}
                    style={{
                        padding: "10px 20px",
                        background: "#59c452",
                        border: "none",
                        borderRadius: "4px",
                        color: "#fff",
                        cursor: "pointer",
                        fontSize: "16px"
                    }}
                >
                    글쓰기
                </button>
            </div>
        </div>
    );
}

export default News;