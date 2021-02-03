package com.example.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author pengyongjie
 * @date 2021/01/30
 */
@Data
@ApiModel("")
public class Student implements Serializable {

    @ApiModelProperty("学生ID")
    private Integer id;


    @ApiModelProperty("学生姓名")
    private String name;


    @ApiModelProperty("学生性别 0-女 1-男")
    private Boolean sex;


    @ApiModelProperty("学生年龄")
    private Integer age;

    private static final long serialVersionUID = 1L;
}