package com.atguigu.campus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.campus.pojo.Grade;
import com.atguigu.campus.service.GradeService;
import com.atguigu.campus.mapper.GradeMapper;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author ziqiu
* @description 针对表【tb_grade】的数据库操作Service实现
* @createDate 2023-02-04 16:09:40
*/
@Service
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade>
    implements GradeService{

}




