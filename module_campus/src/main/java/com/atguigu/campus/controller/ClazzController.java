package com.atguigu.campus.controller;

import com.atguigu.campus.pojo.Clazz;
import com.atguigu.campus.service.ClazzService;
import com.atguigu.campus.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * ClassName: AdminController
 * Package: com.atguigu.campus.controller
 * Description:
 *
 * @author ziqiu
 * @Create: 2023/2/4 - 16:17  16:17
 * @Version: v1.0
 */

@RestController
@RequestMapping("/sms/clazzController")
public class ClazzController {

    @Resource
    private ClazzService clazzService;

    @RequestMapping("/getClazzsByOpr/{pn}/{pageSize}")
    public Result<Object> getClazzsByOpr(@PathVariable("pn") Integer pn,
                                         @PathVariable("pageSize") Integer pageSize,
                                         Clazz clazz) {
        String gradeName = clazz.getGradeName();
        String name = clazz.getName();
        LambdaQueryWrapper<Clazz> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StrUtil.isNotBlank(gradeName), Clazz::getGradeName, gradeName).
                like(StrUtil.isNotBlank(name), Clazz::getName, name).orderByDesc(Clazz::getId);
        Page<Clazz> page = clazzService.page(new Page<>(pn, pageSize), lambdaQueryWrapper);
        return Result.ok(page);
    }

    @PostMapping("/saveOrUpdateClazz")
    public Result<Object> saveOrUpdateClazz(Clazz clazz) {
        Integer id = clazz.getId();
        if (id == null) {
            //添加
            clazzService.save(clazz);
        } else {
            clazzService.update(clazz, new QueryWrapper<Clazz>().eq("id", clazz.getId()));
        }
        return Result.ok();
    }


}
