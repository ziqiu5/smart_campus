package com.atguigu.campus.controller;

import com.atguigu.campus.pojo.Admin;
import com.atguigu.campus.service.AdminService;
import com.atguigu.campus.utils.MD5;
import com.atguigu.campus.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
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
@Api(tags = "管理员控制层")
@RestController
@RequestMapping("/sms/adminController")
public class AdminController {

    @Resource
    private AdminService adminService;

    @ApiOperation("查询管理员信息,分页带条件")
    @GetMapping("/getAllAdmin/{pn}/{pageSize}")
    public Result<Object> getAllAdmin(@ApiParam("当前页码") @PathVariable("pn") Integer pn,
                                      @ApiParam("每页显示记录数") @PathVariable("pageSize") Integer pageSize,
                                      @ApiParam("模糊条件 要查询的管理员姓名") String adminName){

        Page<Admin> page = adminService.page(new Page<>(pn, pageSize), new LambdaQueryWrapper<Admin>()
                .like(StrUtil.isNotBlank(adminName), Admin::getName, adminName).orderByDesc(Admin::getId));
        return Result.ok(page);
    }

    @ApiOperation("单条记录或者批量删除管理员信息")
    @DeleteMapping("/deleteAdmin")
    public Result<Object> deleteAdmin(@ApiParam("请求体中的待删除的管理员id集合") @RequestBody List<Integer> ids){
        if(ids.size() == 1){
            adminService.removeById(ids.get(0));
        }else {
            adminService.removeBatchByIds(ids);
        }
        return Result.ok();
    }

    @ApiOperation("保存或更新管理员信息 当请求体中没有id则为存储 有id时为根据id对管理员信息进行修改")
    @PostMapping("/saveOrUpdateAdmin")
    public Result<Object> saveOrUpdateAdmin(@ApiParam("请求体中封装的管理员json信息") @RequestBody Admin admin){
        Integer id = admin.getId();
        if(id == null){
            admin.setPassword(MD5.encrypt(admin.getPassword()));
            adminService.save(admin);
        }else {
            adminService.update(admin,new LambdaQueryWrapper<Admin>().eq(Admin::getId,id));
        }
        return Result.ok();
    }




}
