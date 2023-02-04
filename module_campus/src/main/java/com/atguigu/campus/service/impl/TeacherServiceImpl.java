package com.atguigu.campus.service.impl;

import com.atguigu.campus.pojo.Student;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.campus.pojo.Teacher;
import com.atguigu.campus.service.TeacherService;
import com.atguigu.campus.mapper.TeacherMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author ziqiu
* @description 针对表【tb_teacher】的数据库操作Service实现
* @createDate 2023-02-04 16:09:47
*/
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService{

    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public Teacher selectTeacherByNameAndPassword(String username, String password) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",username).eq("password",password);
        return teacherMapper.selectOne(queryWrapper);
    }

    @Override
    public Teacher selectAdminById(Long userId) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",userId);
        return teacherMapper.selectOne(queryWrapper);
    }
}




