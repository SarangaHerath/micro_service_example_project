package com.saara.student.service.Impl;

import com.saara.student.dto.request.RequestStudentDto;
import com.saara.student.dto.response.CommonResponse;
import com.saara.student.entity.Student;
import com.saara.student.repository.StudentRepo;
import com.saara.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;

    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public ResponseEntity<CommonResponse> saveStudent(RequestStudentDto requestStudentDto) throws Exception {
        log.info("hit save student service impl, dto: {}", requestStudentDto);
        try {
            if (studentRepo.existsByEmail(requestStudentDto.getEmail())) {
                throw new Exception("Email already exist ");
            } else {
                Student student = Student.builder()
                        .firstName(requestStudentDto.getFirstName())
                        .lastName(requestStudentDto.getLastName())
                        .email(requestStudentDto.getEmail())
                        .build();
                studentRepo.save(student);
                return ResponseEntity.ok(CommonResponse.builder()
                        .data(student)
                        .message("Student save success")
                        .responseCode(HttpStatus.CREATED)
                        .build());
            }
        } catch (Exception e) {
            throw new Exception("Error occur during save student");
        }
    }

    @Override
    public ResponseEntity<CommonResponse> getAllStudent() throws Exception {
        log.info("hit get all student service impl");
        try {
            List<Student> studentList = studentRepo.findAll();
            List<RequestStudentDto> requestStudentDtoList = studentList.stream()
                    .map(student -> RequestStudentDto.builder()
                            .id(student.getId())
                            .email(student.getEmail())
                            .firstName(student.getFirstName())
                            .lastName(student.getLastName())
                            .build())
                    .toList();
            return ResponseEntity.ok(CommonResponse.builder()
                    .data(requestStudentDtoList)
                    .message("Get all student success")
                    .responseCode(HttpStatus.OK)
                    .build());
        } catch (Exception e) {
            throw new Exception("Error occur ducring get all student");
        }
    }
}
