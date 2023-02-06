package com.atguigu.campus.controller;

import com.atguigu.campus.pojo.Clazz;
import com.atguigu.campus.service.ClazzService;
import com.atguigu.campus.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

@Api(tags = "班级控制层")
@RestController
@RequestMapping("/sms/clazzController")
public class ClazzController {

    @Resource
    private ClazzService clazzService;

    @ApiOperation("根据分页带条件查询班级信息")
    @GetMapping("/getClazzsByOpr/{pn}/{pageSize}")
    public Result<Object> getClazzsByOpr(@ApiParam("当前页码") @PathVariable("pn") Integer pn,
                                         @ApiParam("每页显示记录数") @PathVariable("pageSize") Integer pageSize,
                                         @ApiParam("请求参数中模糊查询的条件封装") Clazz clazz) {
        String gradeName = clazz.getGradeName();
        String name = clazz.getName();
        LambdaQueryWrapper<Clazz> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StrUtil.isNotBlank(gradeName), Clazz::getGradeName, gradeName).
                like(StrUtil.isNotBlank(name), Clazz::getName, name).orderByDesc(Clazz::getId);
        Page<Clazz> page = clazzService.page(new Page<>(pn, pageSize), lambdaQueryWrapper);
        return Result.ok(page);
    }

    @ApiOperation("保存或者更新班级信息")
    @PostMapping("/saveOrUpdateClazz")
    public Result<Object> saveOrUpdateClazz(@ApiParam("用实体类封装请求体中的json数据") @RequestBody Clazz clazz) {
        Integer id = clazz.getId();
        if (id == null) {
            //添加
            clazzService.save(clazz);
        } else {
            clazzService.update(clazz, new QueryWrapper<Clazz>().eq("id", clazz.getId()));
        }
        return Result.ok();
    }

    @ApiOperation("单独删除或者批量删除班级信息")
    @DeleteMapping("/deleteClazz")
    public Result<Object> deleteClazz(@ApiParam("请求体中封装的待删除的班级id集合") @RequestBody List<Integer> ids){
        if(ids.size() == 1){
            clazzService.removeById(ids.get(0));
        }else {
            clazzService.removeBatchByIds(ids);
        }
        return Result.ok();
    }


    @ApiOperation("获取所有班级的JSON")
    @GetMapping("/getClazzs")
    public Result<Object> getClazzs(){
        List<Clazz> clazzes = clazzService.list();
        return Result.ok(clazzes);
    }


}
