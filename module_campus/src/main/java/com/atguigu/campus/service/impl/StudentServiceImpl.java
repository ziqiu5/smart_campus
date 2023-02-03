package com.atguigu.campus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.campus.pojo.Student;
import com.atguigu.campus.service.StudentService;
import com.atguigu.campus.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
* @author ziqiu
* @description 针对表【tb_student】的数据库操作Service实现
* @createDate 2023-02-03 21:41:55
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}




