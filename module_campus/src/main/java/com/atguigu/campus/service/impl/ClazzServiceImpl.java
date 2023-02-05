package com.atguigu.campus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.campus.pojo.Clazz;
import com.atguigu.campus.service.ClazzService;
import com.atguigu.campus.mapper.ClazzMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author ziqiu
* @description 针对表【tb_clazz】的数据库操作Service实现
* @createDate 2023-02-04 16:09:37
*/
@Service
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz>
    implements ClazzService{
}




