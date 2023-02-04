package com.atguigu.campus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.campus.pojo.Admin;
import com.atguigu.campus.service.AdminService;
import com.atguigu.campus.mapper.AdminMapper;
import org.apache.el.parser.AstMinus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author ziqiu
* @description 针对表【tb_admin】的数据库操作Service实现
* @createDate 2023-02-04 16:09:29
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

    @Resource
    private AdminMapper adminMapper;

    @Override
    public Admin selectAdminByNameAndPassword(String username, String password) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",username).eq("password",password);
        return adminMapper.selectOne(queryWrapper);
    }

    @Override
    public Admin selectAdminById(Long userId) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",userId);
        return adminMapper.selectOne(queryWrapper);
    }
}




