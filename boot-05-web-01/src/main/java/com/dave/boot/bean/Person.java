package com.dave.boot.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author Xu Rui
 * @date 2021/1/7 15:45
 */
@Data
public class Person {
    private String userName;
    private Integer age;
    private Date birth;
    private Pet pet;
}
