package com.example.demo.service.impl;

import com.example.demo.dao.StudentDao;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @author pengyongjie
 * @date 2021/01/30
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentDao studentDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return studentDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Student record) {
        return studentDao.insert(record);
    }

    @Override
    public int insertSelective(Student record) {
        return studentDao.insertSelective(record);
    }

    @Override
    public Student selectByPrimaryKey(Long id) {
        return studentDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Student record) {
        return studentDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Student record) {
        return studentDao.updateByPrimaryKey(record);
    }
}
