package com.atguigu.campus.service;

import com.atguigu.campus.mapper.AdminMapper;
import com.atguigu.campus.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
* @author ziqiu
* @description 针对表【tb_admin】的数据库操作Service
* @createDate 2023-02-04 16:09:29
*/
public interface AdminService extends IService<Admin> {

    Admin selectAdminByNameAndPassword(String username, String password);

    Admin selectAdminById(Long userId);
}
