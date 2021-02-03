package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author pengyongjie
 * @date 2021/01/30
 */
@Api(tags = "Mysql")
@RestController
@RequestMapping("student")
public class StudentController {


    @Resource
    private StudentService studentService;


    @ApiOperation("根据ID删除学生接口")
    @PostMapping("delete/by-primarykey")
    public void deleteByPrimaryKey(Long id){
        studentService.deleteByPrimaryKey(id);
    }


    @ApiOperation("新增学生信息接口")
    @PostMapping("add")
    public void insert(@RequestBody Student student){
        studentService.insert(student);
    }


    @ApiOperation("新增学生信息接口")
    @PostMapping("addselective")
    public void insertSelective(@RequestBody Student student){
        studentService.insertSelective(student);
    }


    @ApiOperation("新增学生信息接口")
    @GetMapping("get/by-primarykey")
    public Student selectByPrimaryKey(Long id){
        return studentService.selectByPrimaryKey(id);
    }


    @ApiOperation("根据ID动态更新学生信息接口")
    @PostMapping("updateselective/by-primarykey")
    public void updateByPrimaryKeySelective(@RequestBody Student student){
        studentService.updateByPrimaryKeySelective(student);
    }


    @ApiOperation("根据ID更新学生信息接口")
    @PostMapping("update/by-primarykey")
    public void updateByPrimaryKey(@RequestBody Student student){
        studentService.updateByPrimaryKey(student);
    }

    
}
