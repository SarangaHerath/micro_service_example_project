package com.saara.main.service;

import com.saara.main.dto.request.RequestLectureDto;
import com.saara.main.dto.request.RequestStudentDto;
import com.saara.main.dto.response.CommonResponse;
import org.springframework.http.ResponseEntity;

public interface MainRestService {
    ResponseEntity<CommonResponse> saveStudent(RequestStudentDto requestStudentDto) throws Exception;

    ResponseEntity<CommonResponse> saveLecture(RequestLectureDto requestLectureDto) throws Exception;

    ResponseEntity<CommonResponse> getAllStudent() throws Exception;

    ResponseEntity<CommonResponse> getAllLecture() throws Exception;
}
