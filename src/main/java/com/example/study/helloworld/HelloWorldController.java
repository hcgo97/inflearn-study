package com.example.study.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController//레스트컨트롤러쓰면 자동으로 json으로 반환됨
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    //GET
    // /hello-world -> (endpoint)
    @GetMapping("/hello-world")
    public String helloworld(){

        return "Hello World";

    }


    //GET
    // /hello-world -> (endpoint)
    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean(){

        return new HelloWorldBean("Hello World");

    }


    @GetMapping("/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){

        return new HelloWorldBean(String.format("Hello World, %s", name) );

    }


    @GetMapping("/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale){

        return messageSource.getMessage("greeting.message", null, locale);

    }


}
