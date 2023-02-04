package com.atguigu.campus.controller;

import com.atguigu.campus.pojo.Grade;
import com.atguigu.campus.service.GradeService;
import com.atguigu.campus.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@RequestMapping("/sms/gradeController")
public class GradeController {

    @Resource
    private GradeService gradeService;

    @RequestMapping("/getGrades/{pn}/{pageSize}")
    public Result<Object> getGrades(@PathVariable("pn")Integer pn,
                                    @PathVariable("pageSize")Integer pageSize,
                                    @RequestParam(value = "gradeName",required = false) String gradeName){
        Map<String,Object> map = new HashMap<>();
        //判断gradeName是否有值 若有值 则需要根据条件进行分页
        if (gradeName == null) {
            //根据当前页 以及每页显示的记录数 获取Page对象
            Page<Grade> page = gradeService.page(new Page<>(pn, pageSize));
            //将获取到的page对象放到map中 一起封装到Result类 响应到浏览器
            map.put("page",page);
            List<Grade> records = page.getRecords();
            records.forEach(System.out::println);
        }else {
            LambdaQueryWrapper<Grade> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.like(Grade::getName,gradeName).orderByDesc(Grade::getId);
            Page<Grade> page = gradeService.page(new Page<>(pn, pageSize), lambdaQueryWrapper);
            map.put("page",page);
        }
        return Result.ok(map);
    }


}
