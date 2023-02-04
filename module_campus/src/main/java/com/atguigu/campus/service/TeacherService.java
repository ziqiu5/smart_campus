package com.atguigu.campus.service;

import com.atguigu.campus.pojo.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author ziqiu
* @description 针对表【tb_teacher】的数据库操作Service
* @createDate 2023-02-04 16:09:47
*/
public interface TeacherService extends IService<Teacher> {

    Teacher selectTeacherByNameAndPassword(String username, String password);

    Teacher selectAdminById(Long userId);
}
