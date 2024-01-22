package com.saara.student.service;

import com.saara.student.dto.request.RequestStudentDto;
import com.saara.student.dto.response.CommonResponse;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    ResponseEntity<CommonResponse> saveStudent(RequestStudentDto requestStudentDto) throws Exception;

    ResponseEntity<CommonResponse> getAllStudent() throws Exception;
}
