package com.example.study.helloworld;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor //매개변수없는 생성자
public class HelloWorldBean {
    private String message;


//  @Data 써서 세터게터 안해도됨
//
//    public String GetMessage(){
//        return this.message;
//    }
//
//    public String SetMessage(String msg){
//        return this.message;
//    }

//    @AllArgsConstructor 사용시 생략가능
//
//    public HelloWorldBean(String message) {
//        this.message = message;
//    }

}
