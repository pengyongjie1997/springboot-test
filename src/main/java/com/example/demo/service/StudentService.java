package com.example.demo.service;

import com.example.demo.entity.Student;

/**
 * @author pengyongjie
 * @date 2021/01/30
 */
public interface StudentService {
    int deleteByPrimaryKey(Long id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}
