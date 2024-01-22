package com.saara.lectures.service;

import com.saara.lectures.dto.request.RequestLuctureDto;
import com.saara.lectures.dto.response.CommonResponse;
import org.springframework.http.ResponseEntity;

public interface LectureService {
    ResponseEntity<CommonResponse> saveLecture(RequestLuctureDto requestLuctureDto) throws Exception;

    ResponseEntity<CommonResponse> getAllLecture() throws Exception;
}
