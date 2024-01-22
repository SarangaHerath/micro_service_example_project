package com.saara.student.controller;

import com.saara.student.dto.request.RequestStudentDto;
import com.saara.student.dto.response.CommonResponse;
import com.saara.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("api/v1/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save")
    public ResponseEntity<CommonResponse> saveStudent(@RequestBody RequestStudentDto requestStudentDto) throws Exception {
        log.info("hit save student method, dto : {}", requestStudentDto);
        return studentService.saveStudent(requestStudentDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<CommonResponse> getAllStudent() throws Exception {
        log.info("hit get all student method");
        return studentService.getAllStudent();
    }
}
