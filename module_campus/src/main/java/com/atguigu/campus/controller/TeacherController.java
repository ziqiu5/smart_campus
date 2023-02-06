package com.atguigu.campus.controller;

import com.atguigu.campus.pojo.Teacher;
import com.atguigu.campus.service.TeacherService;
import com.atguigu.campus.utils.MD5;
import com.atguigu.campus.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
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
@Api(tags = "教师信息管理控制器")
@RestController
@RequestMapping("/sms/teacherController")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @ApiOperation("获取教师信息,分页带条件")
    @GetMapping("/getTeachers/{pn}/{pageSize}")
    public Result<Object> getTeachers(@ApiParam("当前页码") @PathVariable("pn") Integer pn,
                                      @ApiParam("每页显示的记录数") @PathVariable("pageSize") Integer pageSize,
                                      @ApiParam("请求参数") String name,@ApiParam("请求参数") String clazzName) {
        Page<Teacher> page = teacherService.page(new Page<>(pn, pageSize),
                new LambdaQueryWrapper<Teacher>().like(StrUtil.isNotBlank(name), Teacher::getName, name)
                .like(StrUtil.isNotBlank(clazzName), Teacher::getClazzName, clazzName).orderByDesc(Teacher::getId));
        return Result.ok(page);
    }

    @ApiOperation("单条或批量删除教师信息")
    @DeleteMapping("/deleteTeacher")
    public Result<Object> deleteTeacher(@ApiParam("请求体中的要删除的教师id集合") @RequestBody List<Integer> ids){
        if(ids.size() == 1){
            teacherService.removeById(ids.get(0));
        }else {
            teacherService.removeBatchByIds(ids);
        }
        return Result.ok();
    }

    @ApiOperation("添加或修改Admin信息")
    @PostMapping("/saveOrUpdateTeacher")
    public Result<Object> saveOrUpdateTeacher(@ApiParam("封装到实体类的请求体中json数据") @RequestBody Teacher teacher){
        Integer id = teacher.getId();
        if(id == null){
            //将获取到的密码进行加密 存储到数据库中
            teacher.setPassword(MD5.encrypt(teacher.getPassword()));
            teacherService.save(teacher);
        }else {
            teacherService.update(teacher,new LambdaQueryWrapper<Teacher>().eq(Teacher::getId,id));
        }
        return Result.ok();
    }



}
