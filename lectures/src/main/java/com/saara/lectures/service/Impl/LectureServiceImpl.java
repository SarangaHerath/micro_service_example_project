package com.saara.lectures.service.Impl;

import com.saara.lectures.dto.request.RequestLuctureDto;
import com.saara.lectures.dto.response.CommonResponse;
import com.saara.lectures.entity.Lecture;
import com.saara.lectures.repository.LectureRepo;
import com.saara.lectures.service.LectureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LectureServiceImpl implements LectureService {

    private final LectureRepo lectureRepo;

    public LectureServiceImpl(LectureRepo lectureRepo) {
        this.lectureRepo = lectureRepo;
    }

    @Override
    public ResponseEntity<CommonResponse> saveLecture(RequestLuctureDto requestLuctureDto) throws Exception {
        log.info("hit save lecture service impl, dto: {}", requestLuctureDto);
        try {
            if (lectureRepo.existsByEmail(requestLuctureDto.getEmail())) {
                throw new Exception("Email already exist ");
            } else {
                Lecture lecture = Lecture.builder()
                        .firstName(requestLuctureDto.getFirstName())
                        .lastName(requestLuctureDto.getLastName())
                        .email(requestLuctureDto.getEmail())
                        .build();
                lectureRepo.save(lecture);
                return ResponseEntity.ok(CommonResponse.builder()
                        .data(lecture)
                        .message("Lecture save success")
                        .responseCode(HttpStatus.CREATED)
                        .build());
            }
        } catch (Exception e) {
            throw new Exception("Error occur during save lecture");
        }
    }

    @Override
    public ResponseEntity<CommonResponse> getAllLecture() throws Exception {
        log.info("hit get all lecture service impl");
        try {
            List<Lecture> lectureList = lectureRepo.findAll();
            List<RequestLuctureDto> requestLectureDtoList = lectureList.stream()
                    .map(student -> RequestLuctureDto.builder()
                            .id(student.getId())
                            .email(student.getEmail())
                            .firstName(student.getFirstName())
                            .lastName(student.getLastName())
                            .build())
                    .toList();
            return ResponseEntity.ok(CommonResponse.builder()
                    .data(requestLectureDtoList)
                    .message("Get all lecture success")
                    .responseCode(HttpStatus.OK)
                    .build());
        } catch (Exception e) {
            throw new Exception("Error occur during get all lecture");
        }
    }
}
