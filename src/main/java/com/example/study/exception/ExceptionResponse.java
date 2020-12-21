package com.example.study.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse { //ErrorResponse

    private Date timestamp; //예외 발생시각
    private String message; // 예외 메시지
    private String details; // 예외 상세보기

}
