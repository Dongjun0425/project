package com.shingu.university.service;

import com.shingu.university.domain.StatisticData;
import com.shingu.university.domain.University;
import com.shingu.university.repository.StatisticDataRepository;
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
import java.net.URLEncoder;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticDataBatchService {

    private final StatisticDataRepository statisticDataRepository;
    private final UniversityRepository universityRepository;

    private static final String API_KEY = "vdCZUaw0OvQ9OVGqD6P9+15oBKd6n/rVlXWRTS2Sj6uoLcYjEUsNBBZ5o7RJiCjWrTKezCptYyNzCOGJlUZSHg==";
    private static final int YEAR = 2024;

    // 1. 신입생 경쟁률
    public void fetchAndSaveFreshmanCompetitionRate() {
        fetchAndSave("getComparisonInsideFixedNumberFreshmanCompetitionRate", "신입생경쟁률");
    }

    // 2. 졸업생 취업률
    public void fetchAndSaveGraduateEmploymentRate() {
        fetchAndSave("getNoticeGraduateEmploymentRate", "졸업생취업률");
    }

    // 3. 신입생 충원 현황
    public void fetchAndSaveFreshmanEnsureStatus() {
        fetchAndSave("getComparisonFreshmanEnsureCrntSt", "신입생충원현황");
    }

    // 4. 휴학생 비율
    public void fetchAndSaveLeaveOfAbsenceRate() {
        fetchAndSave("getComparisonStudentOnALeaveOfAbsence", "휴학생비율");
    }

    // 5. 재학생 충원율
    public void fetchAndSaveStudentEnrollmentRate() {
        fetchAndSave("getComparisonEnrolledStudentEnsureRate", "재학생충원율");
    }

    // 6. 외국인 학생 현황
    public void fetchAndSaveForeignStudentStatus() {
        fetchAndSave("getComparisonForeignStudentCrntSt", "외국인학생현황");
    }

    // 실제 API 호출 및 저장 로직 (공통)
    private void fetchAndSave(String apiName, String type) {
        System.out.println("🚀 fetchAndSave(): " + type);
        List<University> universities = universityRepository.findAll();

        for (University university : universities) {
            try {
                String schlId = university.getCode();
                if (schlId == null || schlId.isEmpty()) continue;

                String encodedKey = URLEncoder.encode(API_KEY, "UTF-8");
                String urlStr = "http://openapi.academyinfo.go.kr/openapi/service/rest/StudentService/"
                        + apiName
                        + "?serviceKey=" + encodedKey
                        + "&svyYr=" + YEAR
                        + "&schlId=" + schlId;

                System.out.println("📡 요청 URL: " + urlStr);

                HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/xml");

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) sb.append(line);

                parseAndSave(sb.toString(), type, university);

            } catch (Exception e) {
                System.out.println("❌ 예외 발생 (" + university.getName() + "): " + e.getMessage());
            }
        }
    }

    private void parseAndSave(String xml, String type, University university) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xml)));
            NodeList items = doc.getElementsByTagName("item");

            if (items.getLength() == 0) {
                System.out.println("⚠️ 데이터 없음: " + university.getName());
                return;
            }

            boolean saved = false;

            for (int i = 0; i < items.getLength(); i++) {
                Element el = (Element) items.item(i);
                String yearStr = getTag(el, "svyYr");
                String valueStr = getTag(el, "indctVal1");

                Integer year = parseInt(yearStr);
                Double value = parseDouble(valueStr);

                if (year != null && value != null && year == YEAR) {
                    StatisticData data = new StatisticData();
                    data.setUniversity(university);
                    data.setYear(year);
                    data.setType(type);
                    data.setValue(value);

                    statisticDataRepository.save(data);
                    System.out.println("✅ 저장됨: " + university.getName() + " / " + type + " = " + value);
                    saved = true;
                }
            }

            if (!saved) {
                System.out.println("❌ 저장 안됨 (조건 불충족): " + university.getName());
            }

        } catch (Exception e) {
            System.out.println("❌ XML 파싱 오류 (" + university.getName() + "): " + e.getMessage());
        }
    }

    private String getTag(Element el, String tag) {
        NodeList list = el.getElementsByTagName(tag);
        return list.getLength() > 0 ? list.item(0).getTextContent().trim() : "";
    }

    private Integer parseInt(String val) {
        try {
            return val.isEmpty() ? null : Integer.parseInt(val);
        } catch (Exception e) {
            return null;
        }
    }

    private Double parseDouble(String val) {
        try {
            return val.isEmpty() ? null : Double.parseDouble(val);
        } catch (Exception e) {
            return null;
        }
    }
}
