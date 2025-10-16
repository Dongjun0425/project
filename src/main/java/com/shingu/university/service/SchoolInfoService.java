package com.shingu.university.service;

import com.shingu.university.domain.SchoolInfo;
import com.shingu.university.domain.University;
import com.shingu.university.repository.SchoolInfoRepository;
import com.shingu.university.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
@RequiredArgsConstructor
public class SchoolInfoService {

    private final UniversityRepository universityRepository;
    private final SchoolInfoRepository infoRepository;

    public void fetchAndSaveSchoolInfo() {
        String baseUrl = "http://openapi.academyinfo.go.kr/openapi/service/rest/SchoolInfoService/getSchoolInfo";
        String rawKey = "vdCZUaw0OvQ9OVGqD6P9+15oBKd6n/rVlXWRTS2Sj6uoLcYjEUsNBBZ5o7RJiCjWrTKezCptYyNzCOGJlUZSHg==";
        int year = 2023;

        try {
            String encodedKey = URLEncoder.encode(rawKey, "UTF-8");

            for (int page = 1; page <= 2; page++) {
                String urlStr = baseUrl + "?serviceKey=" + encodedKey +
                        "&pageNo=" + page +
                        "&numOfRows=999" +
                        "&svyYr=" + year;

                URL url = new URL(urlStr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/xml");

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) sb.append(line);
                String xml = sb.toString();

                if (xml.contains("SERVICE KEY IS NOT REGISTERED")) {
                    System.out.println("❌ 인증키 오류: page=" + page);
                    continue;
                }

                parseAndSave(xml, page);
            }
        } catch (Exception e) {
            System.out.println("❌ 예외 발생: " + e.getMessage());
        }
    }

    private void parseAndSave(String xml, int page) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xml)));
            NodeList items = doc.getElementsByTagName("item");

            System.out.println("✅ page " + page + ": " + items.getLength() + "개 항목");

            for (int i = 0; i < items.getLength(); i++) {
                Element el = (Element) items.item(i);
                String schlId = getTag(el, "schlId");

                University university = universityRepository.findByCode(schlId).orElse(null);
                if (university != null) {
                    SchoolInfo info = new SchoolInfo();
                    info.setUniversity(university);
                    info.setSchlNm(getTag(el, "schlNm"));
                    info.setSchlDivNm(getTag(el, "schlDivNm"));
                    info.setSchlEstbDivNm(getTag(el, "schlEstbDivNm"));
                    info.setSchlEstbDt(getTag(el, "schlEstbDt"));
                    info.setPostNoAdrs(getTag(el, "postNoAdrs"));
                    info.setPostNo(getTag(el, "postNo"));
                    info.setSchlRepTpNoCtnt(getTag(el, "schlRepTpNoCtnt"));
                    info.setSchlRepFxNoCtnt(getTag(el, "schlRepFxNoCtnt"));
                    info.setSchlUrlAdrs(getTag(el, "schlUrlAdrs"));

                    infoRepository.save(info);
                    System.out.println("✅ 저장 성공: " + university.getName());
                } else {
                    System.out.println("❌ 매칭 실패: " + schlId);
                }
            }
        } catch (Exception e) {
            System.out.println("❌ XML 파싱 예외: " + e.getMessage());
        }
    }

    private String getTag(Element el, String tag) {
        NodeList list = el.getElementsByTagName(tag);
        return list.getLength() > 0 ? list.item(0).getTextContent() : "";
    }
}
