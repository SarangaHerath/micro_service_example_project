package com.saara.lectures.repository;

import com.saara.lectures.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepo extends JpaRepository<Lecture, Long> {
    boolean existsByEmail(String email);
}
