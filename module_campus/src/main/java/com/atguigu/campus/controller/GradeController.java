package com.atguigu.campus.controller;

import com.atguigu.campus.pojo.Grade;
import com.atguigu.campus.service.GradeService;
import com.atguigu.campus.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public Result<Object> getGrades(@PathVariable("pn") Integer pn,
                                    @PathVariable("pageSize") Integer pageSize,
                                    @RequestParam(value = "gradeName", required = false) String gradeName) {
        //判断gradeName是否有值 若有值 则需要根据条件进行分页
        if (gradeName == null) {
            //根据当前页 以及每页显示的记录数 获取Page对象
            Page<Grade> page = gradeService.page(new Page<>(pn, pageSize),new LambdaQueryWrapper<Grade>().orderByDesc(Grade::getId));
            return Result.ok(page);
        }
        LambdaQueryWrapper<Grade> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Grade::getName, gradeName).orderByDesc(Grade::getId);
        Page<Grade> page = gradeService.page(new Page<>(pn, pageSize), lambdaQueryWrapper);
        return Result.ok(page);
    }

    @PostMapping("/saveOrUpdateGrade")
    public Result<Object> saveOrUpdateGrade(@RequestBody Grade grade) {
        Integer id = grade.getId();
        if (id == null) {
            gradeService.save(grade);
        } else {
            gradeService.update(grade, new LambdaQueryWrapper<Grade>().eq(Grade::getId, id));
        }
        return Result.ok();
    }

    @DeleteMapping("/deleteGrade")
    public Result<Object> deleteGrade(@RequestBody List<Integer> ids){
        if(ids.size() == 1){
            //单条记录的删除
            gradeService.removeById(ids.get(0));
        }else {
            gradeService.removeBatchByIds(ids);
        }
        return Result.ok();
    }


}
