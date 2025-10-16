import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function NewsInput() {
  const navigate = useNavigate();
  const [title, setTitle] = useState('');
  const [category, setCategory] = useState('학교');
  const [content, setContent] = useState('');
  const [imageFile, setImageFile] = useState(null);
  const [imagePreview, setImagePreview] = useState(null);

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    setImageFile(file);
    if (file) {
      const reader = new FileReader();
      reader.onload = (event) => {
        setImagePreview(event.target.result);
      };
      reader.readAsDataURL(file);
    } else {
      setImagePreview(null);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!title.trim() || !content.trim()) {
      alert("제목과 내용을 입력해주세요.");
      return;
    }
    const formData = new FormData();
    formData.append('title', title);
    formData.append('category', category);
    formData.append('content', content);
    if (imageFile) {
      formData.append('image_url', imageFile);
    }
    try {
      const response = await fetch('http://localhost:8080/api/news', {
        method: 'POST',
        body: formData,
      });
      if (response.ok) {
        alert("뉴스가 등록되었습니다.");
        navigate('/news');
      } else {
        alert("뉴스 등록에 실패했습니다.");
      }
    } catch (error) {
      console.error("뉴스 등록 중 오류:", error);
      alert("뉴스 등록 중 오류가 발생했습니다.");
    }
  };

  return (
    <div style={{ maxWidth: "800px", margin: "0 auto", padding: "40px 20px", background: "#fff" }}>
      <h1 style={{ textAlign: "center", marginBottom: "20px" }}>뉴스 작성</h1>
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: "20px" }}>
          <label style={{ display: "block", marginBottom: "8px", fontWeight: "bold" }}>제목</label>
          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            placeholder="제목을 입력하세요."
            style={{ width: "100%", padding: "10px", fontSize: "16px", border: "1px solid #ccc", borderRadius: "4px" }}
            required
          />
        </div>
        <div style={{ marginBottom: "20px" }}>
          <label style={{ display: "block", marginBottom: "8px", fontWeight: "bold" }}>카테고리</label>
          <select
            value={category}
            onChange={(e) => setCategory(e.target.value)}
            style={{
              width: "820px",
              padding: "10px",
              fontSize: "16px",
              border: "1px solid #ccc",
              borderRadius: "4px",
              height: "42px",
              boxSizing: "border-box",
              WebkitAppearance: "none",
              MozAppearance: "none",
              appearance: "none"
            }}
          >
            <option value="학교">학교</option>
            <option value="음식">음식</option>
            <option value="도서">도서</option>
          </select>
        </div>
        <div style={{ marginBottom: "20px" }}>
          <label style={{ display: "block", marginBottom: "8px", fontWeight: "bold" }}>내용</label>
          <textarea
            value={content}
            onChange={(e) => setContent(e.target.value)}
            placeholder="내용을 입력하세요."
            rows="10"
            style={{ width: "100%", padding: "10px", fontSize: "16px", border: "1px solid #ccc", borderRadius: "4px" }}
            required
          />
        </div>
        <div style={{ marginBottom: "20px", display: "flex", alignItems: "center" }}>
          <label style={{ fontWeight: "bold", marginRight: "10px" }}>이미지 첨부</label>
          <input type="file" accept="image/*" onChange={handleImageChange} />
        </div>
        {imagePreview && (
          <div style={{ marginBottom: "20px" }}>
            <img src={imagePreview} alt="미리보기" style={{ maxWidth: "100%", height: "auto" }} />
          </div>
        )}
        <div style={{ display: "flex", gap: "10px" }}>
          <button
            type="button"
            onClick={() => navigate('/news')}
            style={{
              flex: 1,
              padding: "12px",
              background: "#ccc",
              color: "#333",
              fontSize: "18px",
              border: "none",
              borderRadius: "4px",
              cursor: "pointer"
            }}
          >
            취소
          </button>
          <button
            type="submit"
            style={{
              flex: 1,
              padding: "12px",
              background: "#59c452",
              color: "#fff",
              fontSize: "18px",
              border: "none",
              borderRadius: "4px",
              cursor: "pointer"
            }}
          >
            등록
          </button>
        </div>
      </form>
    </div>
  );
}

export default NewsInput;