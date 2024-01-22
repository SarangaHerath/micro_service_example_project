package com.saara.main.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RequestLectureDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
