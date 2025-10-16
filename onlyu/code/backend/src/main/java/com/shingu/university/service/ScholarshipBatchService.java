package com.shingu.university.service;

import com.shingu.university.domain.ScholarshipData;
import com.shingu.university.domain.University;
import com.shingu.university.repository.ScholarshipRepository;
import com.shingu.university.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class ScholarshipBatchService {

    private final UniversityRepository universityRepository;
    private final ScholarshipRepository scholarshipRepository;

    public void fetchAndSaveScholarshipForAllUniv() {
        System.out.println("🎓 fetchAndSaveScholarshipForAllUniv() 실행됨");

        String baseUrl = "http://openapi.academyinfo.go.kr/openapi/service/rest/FinancesService/getComparisonScholarshipBenefitCrntSt";
        String serviceKey = "vdCZUaw0OvQ9OVGqD6P9%2B15oBKd6n%2FrVlXWRTS2Sj6uoLcYjEUsNBBZ5o7RJiCjWrTKezCptYyNzCOGJlUZSHg%3D%3D";
        int year = 2024;

        for (University university : universityRepository.findAll()) {
            try {
                String urlStr = baseUrl + "?serviceKey=" + serviceKey +
                        "&svyYr=" + year + "&schlId=" + university.getCode();
                System.out.println("📡 요청 URL: " + urlStr);

                HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/xml");

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                String xml = sb.toString();

                if (xml.contains("<resultMsg>NODATA_ERROR</resultMsg>")) {
                    System.out.println("⚠️ 장학금 데이터 없음: " + university.getName());
                    continue;
                }

                parseAndSaveScholarshipData(xml, university, year);

            } catch (Exception e) {
                System.out.println("❌ 장학금 처리 중 오류: " + e.getMessage());
            }
        }
    }

    private void parseAndSaveScholarshipData(String xml, University university, int year) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xml)));
            NodeList items = doc.getElementsByTagName("item");

            for (int i = 0; i < items.getLength(); i++) {
                Element el = (Element) items.item(i);
                String amountStr = getTagValue(el, "indctVal1");

                if (amountStr != null && !amountStr.isBlank()) {
                    ScholarshipData data = new ScholarshipData();
                    data.setUniversity(university);
                    data.setYear(year);
                    data.setAmount(Double.parseDouble(amountStr));

                    scholarshipRepository.save(data);
                    System.out.println("✅ 장학금 저장: " + university.getName() + " → " + amountStr);
                } else {
                    System.out.println("⚠️ 값 없음: " + university.getName());
                }
            }
        } catch (Exception e) {
            System.out.println("❌ XML 파싱 실패: " + e.getMessage());
        }
    }

    private String getTagValue(Element el, String tag) {
        NodeList list = el.getElementsByTagName(tag);
        if (list.getLength() > 0) {
            return list.item(0).getTextContent().trim();
        }
        return null;
    }
}
