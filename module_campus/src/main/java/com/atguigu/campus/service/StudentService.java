package com.atguigu.campus.service;

import com.atguigu.campus.pojo.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author ziqiu
* @description 针对表【tb_student】的数据库操作Service
* @createDate 2023-02-04 16:09:44
*/
public interface StudentService extends IService<Student> {

    Student selectStudentByNameAndPassword(String username, String password);

    Student selectAdminById(Long userId);
}
