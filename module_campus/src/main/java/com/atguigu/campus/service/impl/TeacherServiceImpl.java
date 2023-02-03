package com.atguigu.campus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.campus.pojo.Teacher;
import com.atguigu.campus.service.TeacherService;
import com.atguigu.campus.mapper.TeacherMapper;
import org.springframework.stereotype.Service;

/**
* @author ziqiu
* @description 针对表【tb_teacher】的数据库操作Service实现
* @createDate 2023-02-03 21:42:00
*/
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService{

}




