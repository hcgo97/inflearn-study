package com.example.study.exception;

import com.example.study.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice //컨트롤러에서 실행될때 이 클래스가 제일먼저 실행됨 (모든 예외사항에서 제일 먼저 걸러져서 처리됨)
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    //기본에러 (Exception.class -> 어떤 클래스가 싦행되도 일단 이 에러 뜸)
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //사용자가 존재하지않을때
    @ExceptionHandler(UserNotFoundException.class) //UserNotFoundException 이 발생하면 여기 에러뜸
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "유효성 검사 실패", ex.getBindingResult().toString() );

        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
