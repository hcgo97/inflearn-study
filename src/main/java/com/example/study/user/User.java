package com.example.study.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(value = {"password"})
public class User {
    private Integer id;

    @Size(min=2, message = "메롱")
    private String name;
    @Past
    private Date joinDate;


//    @JsonIgnore
    private String password;
//    @JsonIgnore
    private String ssn; //주민등록번호

}
