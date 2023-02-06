package com.atguigu.campus.controller;

import com.atguigu.campus.pojo.Student;
import com.atguigu.campus.service.StudentService;
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
@Api(tags = "学生控制层")
@RestController
@RequestMapping("/sms/studentController")
public class StudentController {

    @Resource
    private StudentService studentService;

    @ApiOperation("分页带条件查询学生信息")
    @GetMapping("/getStudentByOpr/{pn}/{pageSize}")
    public Result<Object> getStudentByOpr(@ApiParam("当前页码") @PathVariable("pn") Integer pn,
                                          @ApiParam("每页显示的记录数") @PathVariable("pageSize") Integer pageSize,
                                          @ApiParam("请求参数中带查询的模糊条件") String name,String clazzName){

        Page<Student> page = studentService.page(new Page<>(pn, pageSize),
                new LambdaQueryWrapper<Student>().like(StrUtil.isNotBlank(name), Student::getName, name)
                        .like(StrUtil.isNotBlank(clazzName), Student::getClazzName, clazzName)
                        .orderByDesc(Student::getId));

        return Result.ok(page);
    }

    @ApiOperation("单独或批量删除学生信息")
    @DeleteMapping("/delStudentById")
    public Result<Object> delStudentById(@ApiParam("请求体中封装的待删除的学生id集合") @RequestBody List<Integer> ids){
        if(ids.size() == 1){
            studentService.removeById(ids.get(0));
        }else {
            studentService.removeBatchByIds(ids);
        }
        return Result.ok();
    }

    @ApiOperation("存储或者更新学生信息")
    @PostMapping("/addOrUpdateStudent")
    public Result<Object> addOrUpdateStudent(@ApiParam("封装到实体类的请求体中json数据") @RequestBody Student student){
        Integer id = student.getId();
        if(id == null){
            //将获取到的密码进行加密 存储到数据库中
            student.setPassword(MD5.encrypt(student.getPassword()));
            //添加学生
            studentService.save(student);
        }else {
            studentService.update(student,new LambdaQueryWrapper<Student>().eq(Student::getId,id));
        }
        return Result.ok();
    }





}
