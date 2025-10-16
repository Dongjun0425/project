package com.shingu.university.service;

import com.shingu.university.domain.TuitionData;
import com.shingu.university.domain.University;
import com.shingu.university.repository.TuitionDataRepository;
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
public class TuitionBatchService {

    private final UniversityRepository universityRepository;
    private final TuitionDataRepository tuitionDataRepository;

    public void fetchAndSaveTuitionForAllUniv() {
        System.out.println("🚀 fetchAndSaveTuitionForAllUniv() 실행됨");

        String baseUrl = "http://openapi.academyinfo.go.kr/openapi/service/rest/FinancesService/getComparisonTuitionCrntSt";
        String serviceKey = "vdCZUaw0OvQ9OVGqD6P9%2B15oBKd6n%2FrVlXWRTS2Sj6uoLcYjEUsNBBZ5o7RJiCjWrTKezCptYyNzCOGJlUZSHg%3D%3D";
        int year = 2023;

        for (University university : universityRepository.findAll()) {
            String schlId = university.getCode();

            try {
                String urlStr = baseUrl + "?serviceKey=" + serviceKey + "&svyYr=" + year + "&schlId=" + schlId;
                System.out.println("📡 요청 URL: " + urlStr);

                HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/xml");

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) sb.append(line);
                String xml = sb.toString();

                if (xml.contains("SERVICE KEY IS NOT REGISTERED")) {
                    System.out.println("❌ 인증키 오류 발생");
                    return;
                }

                System.out.println("📦 응답 XML 수신 완료");

                parseAndSave(xml, university, year);

            } catch (Exception e) {
                System.out.println("❌ 예외 발생 (" + university.getName() + "): " + e.getMessage());
            }
        }
    }

    private void parseAndSave(String xml, University university, int year) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xml)));
            NodeList items = doc.getElementsByTagName("item");

            if (items.getLength() == 0) {
                System.out.println("⚠️ 데이터 없음: " + university.getName());
                return;
            }

            Element el = (Element) items.item(0);
            String indctVal = getTag(el, "indctVal1");

            System.out.println("💬 indctVal1 = " + indctVal + " (" + university.getName() + ")");

            if (indctVal.isEmpty()) {
                System.out.println("⚠️ 등록금 값 없음: " + university.getName());
                return;
            }

            TuitionData data = new TuitionData();
            data.setUniversity(university);
            data.setYear(year);
            data.setTotalAmount(parseDouble(indctVal)); // indctVal1만 사용
            data.setCollegeAmount(null);
            data.setGradAmount(null);

            tuitionDataRepository.save(data);
            System.out.println("✅ 등록금 저장: " + university.getName() + " → " + indctVal);

        } catch (Exception e) {
            System.out.println("❌ XML 파싱 예외: " + e.getMessage());
        }
    }

    private String getTag(Element el, String tag) {
        NodeList list = el.getElementsByTagName(tag);
        return list.getLength() > 0 ? list.item(0).getTextContent().trim() : "";
    }

    private Double parseDouble(String value) {
        try {
            return value.isEmpty() ? null : Double.parseDouble(value.replace(",", ""));
        } catch (Exception e) {
            return null;
        }
    }
}
