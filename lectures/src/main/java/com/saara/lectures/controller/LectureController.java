package com.saara.lectures.controller;

import com.saara.lectures.dto.request.RequestLuctureDto;
import com.saara.lectures.dto.response.CommonResponse;
import com.saara.lectures.service.LectureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("api/v1/lecture")
public class LectureController {
    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @PostMapping("/save")
    public ResponseEntity<CommonResponse> saveLecture(@RequestBody RequestLuctureDto requestLuctureDto) throws Exception {
        log.info("hit lecture save method, dto :{}", requestLuctureDto);
        return lectureService.saveLecture(requestLuctureDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<CommonResponse> getAllLecture() throws Exception {
        log.info("hit get all lecture method");
        return lectureService.getAllLecture();
    }
}
