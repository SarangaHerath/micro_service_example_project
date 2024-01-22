package com.saara.main.controller;

import com.saara.main.dto.request.RequestLectureDto;
import com.saara.main.dto.request.RequestStudentDto;
import com.saara.main.dto.response.CommonResponse;
import com.saara.main.service.MainRestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/main")
@Slf4j
public class MainRestController {
    private final MainRestService mainRestService;

    public MainRestController(MainRestService mainRestService) {
        this.mainRestService = mainRestService;
    }

    @PostMapping("/saveStudent")
    public ResponseEntity<CommonResponse> saveStudent(@RequestBody RequestStudentDto requestStudentDto) throws Exception {
        return mainRestService.saveStudent(requestStudentDto);
    }
    @PostMapping("/saveLecture")
    public ResponseEntity<CommonResponse> saveLecture(@RequestBody RequestLectureDto requestLectureDto) throws Exception {
        return mainRestService.saveLecture(requestLectureDto);
    }
    @GetMapping("/getAllStudent")
    public ResponseEntity<CommonResponse> getAllStudent() throws Exception {
        return mainRestService.getAllStudent();
    }
    @GetMapping("/getAllLecture")
    public ResponseEntity<CommonResponse> getAllLecture() throws Exception {
        return mainRestService.getAllLecture();
    }
}
