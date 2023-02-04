package com.atguigu.campus.service.impl;

import com.atguigu.campus.pojo.Admin;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.campus.pojo.Student;
import com.atguigu.campus.service.StudentService;
import com.atguigu.campus.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author ziqiu
* @description 针对表【tb_student】的数据库操作Service实现
* @createDate 2023-02-04 16:09:44
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Student selectStudentByNameAndPassword(String username, String password) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",username).eq("password",password);
        return studentMapper.selectOne(queryWrapper);
    }

    @Override
    public Student selectAdminById(Long userId) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",userId);
        return studentMapper.selectOne(queryWrapper);
    }
}




