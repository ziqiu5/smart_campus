package com.atguigu.campus.controller;

import com.atguigu.campus.pojo.Grade;
import com.atguigu.campus.service.GradeService;
import com.atguigu.campus.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

@Api(tags = "年级控制层")
@RestController
@RequestMapping("/sms/gradeController")
public class GradeController {

    @Resource
    private GradeService gradeService;

    @ApiOperation("分页带条件查询年级信息")
    @GetMapping("/getGrades/{pn}/{pageSize}")
    public Result<Object> getGrades(@ApiParam("当前页码") @PathVariable("pn") Integer pn,
                                    @ApiParam("每页显示的记录数") @PathVariable("pageSize") Integer pageSize,
                                    @ApiParam("请求参数中带查询的模糊条件") String gradeName) {
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

    @ApiOperation("存储或者更新年级信息")
    @PostMapping("/saveOrUpdateGrade")
    public Result<Object> saveOrUpdateGrade(@ApiParam("封装到实体类的请求体中json数据") @RequestBody Grade grade) {
        Integer id = grade.getId();
        if (id == null) {
            gradeService.save(grade);
        } else {
            gradeService.update(grade, new LambdaQueryWrapper<Grade>().eq(Grade::getId, id));
        }
        return Result.ok();
    }

    @ApiOperation("单独或批量删除年级信息")
    @DeleteMapping("/deleteGrade")
    public Result<Object> deleteGrade(@ApiParam("请求体中封装的待删除的年级id集合") @RequestBody List<Integer> ids){
        if(ids.size() == 1){
            //单条记录的删除
            gradeService.removeById(ids.get(0));
        }else {
            gradeService.removeBatchByIds(ids);
        }
        return Result.ok();
    }

    @ApiOperation("获取所有年级的JSON")
    @GetMapping("/getGrades")
    public Result<Object> getGrades(){
        List<Grade> grades = gradeService.list(new LambdaQueryWrapper<Grade>().orderByDesc(Grade::getId));
        return Result.ok(grades);
    }


}
