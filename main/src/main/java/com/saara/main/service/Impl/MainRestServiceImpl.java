package com.saara.main.service.Impl;

import com.saara.main.dto.request.RequestLectureDto;
import com.saara.main.dto.request.RequestStudentDto;
import com.saara.main.dto.response.CommonResponse;
import com.saara.main.service.MainRestService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MainRestServiceImpl implements MainRestService {

    private final RestTemplate restTemplate;
    private final String studentUrl = "http://localhost:8080/api/v1/student";
    private final String lectureUrl = "http://localhost:8081/api/v1/lecture";

    public MainRestServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<CommonResponse> saveStudent(RequestStudentDto requestStudentDto) throws Exception {
        try {
            // Use postForObject for making a POST request
            CommonResponse commonResponse = restTemplate.postForObject(studentUrl + "/save", requestStudentDto, CommonResponse.class);

            // Assuming CommonResponse has appropriate constructor and getters
            return ResponseEntity.ok(new CommonResponse(commonResponse.getData(), commonResponse.getMessage(), commonResponse.getResponseCode()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error occurred during save student", e);
        }
    }


    @Override
    public ResponseEntity<CommonResponse> saveLecture(RequestLectureDto requestLectureDto) throws Exception {
        try {
            // Create a HttpEntity with the request body
            HttpEntity<RequestLectureDto> requestEntity = new HttpEntity<>(requestLectureDto);

            // Use exchange method to send a POST request
            ResponseEntity<CommonResponse> responseEntity = restTemplate.exchange(
                    lectureUrl + "/save",
                    HttpMethod.POST,
                    requestEntity,
                    CommonResponse.class
            );

            // Assuming CommonResponse has appropriate constructor and getters
            CommonResponse commonResponse = responseEntity.getBody();

            return ResponseEntity.ok(new CommonResponse(commonResponse.getData(), commonResponse.getMessage(), commonResponse.getResponseCode()));
        } catch (Exception e) {
            throw new Exception("Error occurred during save lecture", e);
        }
    }

    @Override
    public ResponseEntity<CommonResponse> getAllStudent() throws Exception {
        try {
            CommonResponse commonResponse = restTemplate.getForObject(studentUrl + "/getAll", CommonResponse.class);
            return ResponseEntity.ok(CommonResponse.builder()
                    .data(commonResponse.data)
                    .message(commonResponse.message)
                    .responseCode(commonResponse.responseCode)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("error occur during get all student");
        }
    }

    @Override
    public ResponseEntity<CommonResponse> getAllLecture() throws Exception {
        try {
            CommonResponse commonResponse = restTemplate.getForObject(lectureUrl + "/getAll", CommonResponse.class);
            return ResponseEntity.ok(CommonResponse.builder()
                    .data(commonResponse.data)
                    .message(commonResponse.message)
                    .responseCode(commonResponse.responseCode)
                    .build());
        } catch (Exception e) {
            throw new Exception("error occur during get all lecture");
        }
    }
}
