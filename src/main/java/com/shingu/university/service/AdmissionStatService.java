package com.shingu.university.service;

import com.shingu.university.domain.AdmissionStat;
import com.shingu.university.domain.University;
import com.shingu.university.repository.AdmissionStatRepository;
import com.shingu.university.repository.UniversityRepository;
import org.springframework.stereotype.Service;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@Service
public class AdmissionStatService {

    private final AdmissionStatRepository admissionStatRepository;
    private final UniversityRepository universityRepository;

    private static final String API_URL = "https://openapi.gg.go.kr/Enschscritypeselectn";
    private static final String API_KEY = "2ec92153acf7480bb7e45e8f2ed4381d";
    private static final int YEAR = 2023;

    public AdmissionStatService(AdmissionStatRepository admissionStatRepository, UniversityRepository universityRepository) {
        this.admissionStatRepository = admissionStatRepository;
        this.universityRepository = universityRepository;
    }

    public void fetchAndSaveAdmissionStats() {
        int page = 1;
        boolean hasNext = true;

        while (hasNext) {
            try {
                StringBuilder urlBuilder = new StringBuilder(API_URL);
                urlBuilder.append("?" + URLEncoder.encode("KEY", "UTF-8") + "=" + URLEncoder.encode(API_KEY, "UTF-8"));
                urlBuilder.append("&" + URLEncoder.encode("Type", "UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8"));
                urlBuilder.append("&" + URLEncoder.encode("pIndex", "UTF-8") + "=" + page);
                urlBuilder.append("&" + URLEncoder.encode("pSize", "UTF-8") + "=" + 1000);
                urlBuilder.append("&" + URLEncoder.encode("schlyr", "UTF-8") + "=" + YEAR);

                URL url = new URL(urlBuilder.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    responseBuilder.append(line);
                }
                br.close();

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(new java.io.ByteArrayInputStream(responseBuilder.toString().getBytes()));

                NodeList nodeList = doc.getElementsByTagName("row");

                if (nodeList.getLength() == 0) {
                    hasNext = false;
                    break;
                }

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);

                    String universityName = getTagValue("SCHOOL_NM", element);

                    // 1. DB에서 유사도가 가장 높은 대학 찾기
                    Optional<University> universityOpt = findMostSimilarByName(universityName);

                    if (universityOpt.isEmpty()) {
                        saveUnmatchedUniversityName(universityName);
                        continue;
                    }

                    AdmissionStat stat = new AdmissionStat();
                    stat.setUniversity(universityOpt.get());
                    stat.setYear(YEAR);
                    stat.setSchoolName(universityName);
                    stat.setSchoolKind(getTagValue("SCHOOL_KIND_NM", element));
                    stat.setFoundDiv(getTagValue("FOUND_DIV_NM", element));
                    stat.setCapacityDivision(getTagValue("PSN_CAPA_DIV_NM", element));
                    stat.setEntranceType(getTagValue("SCRI_TYPE_NM", element));
                    stat.setScriLargeClass(getTagValue("SCRI_LRGE_CLASS_NM", element));
                    stat.setScriMedClass(getTagValue("SCRI_MED_CLASS_NM", element));
                    stat.setScriSmallClass(getTagValue("SCRI_SM_CLASS_NM", element));

                    stat.setAnytimeRecruitCount(parseInt(getTagValue("ANYTM_RECRUT_PSN_CNT", element)));
                    stat.setAnytimeRegisterCount(parseInt(getTagValue("ANYTM_REGIST_PSN_CNT", element)));
                    stat.setAnytimeRate(parseDouble(getTagValue("ANYTM_REGIST_RT", element)));

                    stat.setRegrRecruitCount(parseInt(getTagValue("REGR_RECRUT_PSN_CNT", element)));
                    stat.setRegrRegisterCount(parseInt(getTagValue("REGR_REGIST_PSN_CNT", element)));
                    stat.setRegrRate(parseDouble(getTagValue("REGR_REGIST_RT", element)));

                    stat.setAddRecruitCount(parseInt(getTagValue("ADD_RECRUT_PSN_CNT", element)));
                    stat.setAddRegisterCount(parseInt(getTagValue("ADD_REGIST_PSN_CNT", element)));
                    stat.setAddRate(parseDouble(getTagValue("ADD_REGIST_RT", element)));

                    stat.setLastRecruitCount(parseInt(getTagValue("LAST_RECRUT_PSN_CNT", element)));
                    stat.setLastRegisterCount(parseInt(getTagValue("LAST_REGIST_PSN_CNT", element)));
                    stat.setRegistrationRate(parseDouble(getTagValue("LAST_REGIST_RT", element)));

                    admissionStatRepository.save(stat);
                }

                page++;
            } catch (Exception e) {
                System.out.println("❌ 입학전형 등록률 API 예외 발생: " + e.getMessage());
                break;
            }
        }
    }

    // ----------- [핵심] 유사 매칭 함수 (Levenshtein 거리 기반) ----------
    private Optional<University> findMostSimilarByName(String targetName) {
        List<University> universities = universityRepository.findAll();
        University mostSimilar = null;
        int minDistance = Integer.MAX_VALUE;
        for (University univ : universities) {
            int dist = levenshtein(univ.getName(), targetName);
            if (dist < minDistance) {
                minDistance = dist;
                mostSimilar = univ;
            }
        }
        // 예시로, 최소 거리가 4 이내일 때만 매칭(너무 다른 건 unmatched 처리)
        if (mostSimilar != null && minDistance <= 4) {
            return Optional.of(mostSimilar);
        } else {
            return Optional.empty();
        }
    }

    // Levenshtein 최소편집거리 계산 함수
    private int levenshtein(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= s2.length(); j++) dp[0][j] = j;
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j - 1],
                            Math.min(dp[i][j - 1], dp[i - 1][j])
                    );
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    private void saveUnmatchedUniversityName(String universityName) {
        try (FileWriter writer = new FileWriter("unmatched_university.txt", true)) {
            writer.write(universityName + "\n");
        } catch (IOException e) {
            System.out.println("❌ 대학명 저장 파일쓰기 오류: " + e.getMessage());
        }
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent().trim();
        }
        return null;
    }

    private Double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return 0.0;
        }
    }

    private Integer parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }
}
