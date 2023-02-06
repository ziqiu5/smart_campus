package com.atguigu.campus.controller;

import com.atguigu.campus.pojo.Admin;
import com.atguigu.campus.pojo.LoginForm;
import com.atguigu.campus.pojo.Student;
import com.atguigu.campus.pojo.Teacher;
import com.atguigu.campus.service.AdminService;
import com.atguigu.campus.service.StudentService;
import com.atguigu.campus.service.TeacherService;
import com.atguigu.campus.utils.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 将封装在result类中的数据以json格式返回给前端浏览器
 *
 * @author ziqiu
 */
@Api(tags = "系统控制层")
@RestController
@RequestMapping("/sms/system")
public class SystemController {

    @Resource
    private AdminService adminService;

    @Resource
    private StudentService studentService;

    @Resource
    private TeacherService teacherService;

    /**
     * 获取验证码图片响应到浏览器,并将验证码中的值保存到session域中 用于用户登录时/login校验
     */
    @ApiOperation("获取验证码图片")
    @RequestMapping("/getVerifiCodeImage")
    public void getVerifiCodeImage(HttpSession session, HttpServletResponse response) throws IOException {
        //通过工具类CreateVerifiCodeImage 获得验证码图片
        BufferedImage verifiCodeImage = CreateVerifiCodeImage.getVerifiCodeImage();
        //获取验证码图片中的值 并保存在session域中 用于用于登录时校验
        String code = new String(CreateVerifiCodeImage.getVerifiCode());
        session.setAttribute("code", code);
        //将获取到的验证码图片响应到浏览器
        ImageIO.write(verifiCodeImage, "JPG", response.getOutputStream());
    }

    /**
     * 登录:进行验证码以及用户输入的账号密码进行校验
     *
     * @return 将校验的结果数据封装到Result类中返回给浏览器 若用户登录成功 则根据id和用户类型生成一个token放在Result类一起返回给浏览器
     */
    @ApiOperation("登录功能 登陆成功将查询到的用户信息、用户类型 并封装id和userType成token 一起响应到浏览器")
    @PostMapping("/login")
    public Result<Object> login(@ApiParam("封装到实体类中请求体的json数据") @RequestBody LoginForm loginForm, HttpSession session) {
        //校验验证码:
        String userInputCode = loginForm.getVerifiCode();
        //获取session中存放的验证码中的值
        String code = session.getAttribute("code").toString();
        //判断session中的验证码的值是否还在 若时间太长 会失效
        if (code == null || "".equals(code)) {
            return Result.fail().message("验证码失效,请重新输入");
        }
        //判断用户输入验证码与实际验证码的值是否相等
        if (!userInputCode.equalsIgnoreCase(code)) {
            return Result.fail().message("验证码输入有误");
        }
        //验证码输入正确后 将session中的验证码销毁
        session.removeAttribute("code");
        //获取用户类型 根据不同的用户判断输入的账号 密码 是否正确
        Integer userType = loginForm.getUserType();
        Map<String, Object> map = new LinkedHashMap<>();
        if (userType == 1) {
            Admin admin = adminService.selectAdminByNameAndPassword(loginForm.getUsername(), MD5.encrypt(loginForm.getPassword()));
            //若查询结果为null 则数据库中查无相应的账号和密码 登录失败
            if (admin == null) {
                return Result.fail().message("账号或密码有误");
            }
            //登录成功 将用户id与用户类型 封装为一个 token 响应到浏览器
            //让浏览器通过token再发送请求来进行解析 告诉前端应该前往哪个用户以及哪个类型用户的首页
            String token = JwtHelper.createToken(admin.getId().longValue(), userType);
            map.put("token", token);
        } else if (userType == 2) {
            Student student = studentService.selectStudentByNameAndPassword(loginForm.getUsername(), MD5.encrypt(loginForm.getPassword()));
            if (student == null) {
                return Result.fail().message("账号或密码有误");
            }
            String token = JwtHelper.createToken(student.getId().longValue(), userType);
            map.put("token", token);
        } else {
            Teacher teacher = teacherService.selectTeacherByNameAndPassword(loginForm.getUsername(), MD5.encrypt(loginForm.getPassword()));
            if (teacher == null) {
                return Result.fail().message("账号或密码有误");
            }
            String token = JwtHelper.createToken(teacher.getId().longValue(), userType);
            map.put("token", token);
        }
        return Result.ok(map);
    }

    /**
     * 解析 浏览器发送来的请求头中的token
     *
     * @param token 浏览器发送来的token
     * @return 封装数据到Result类 响应给浏览器
     */
    @ApiOperation("将请求头中的token解析成id和用户类型 并根据id和类型查询用户信息 将信息和用户类型数据一起返回")
    @RequestMapping("/getInfo")
    public Result<Object> getInfo(@ApiParam("请求头中的token数据") @RequestHeader("token") String token) {
        //判断token是否还有效 返回为true表示已经失效
        if (JwtHelper.isExpiration(token)) {
            return Result.build(null, ResultCodeEnum.TOKEN_ERROR);
        }
        //解析token 获取用户id 与 用户类型
        Long userId = JwtHelper.getUserId(token);
        Integer userType = JwtHelper.getUserType(token);
        if (userType == null) {
            return Result.build(null, ResultCodeEnum.TOKEN_ERROR);
        }
        //通过不同的用户类型 根据用户id 查询用户信息 并放到Result类中 响应给浏览器
        Map<String, Object> map = new LinkedHashMap<>();
        if (userType == 1) {
            Admin admin = adminService.selectAdminById(userId);
            map.put("user", admin);
            map.put("userType", userType);
        } else if (userType == 2) {
            Student student = studentService.selectAdminById(userId);
            map.put("user", student);
            map.put("userType", userType);
        } else {
            Teacher teacher = teacherService.selectAdminById(userId);
            map.put("user", teacher);
            map.put("userType", userType);
        }
        return Result.ok(map);
    }

    @ApiOperation("上传头像")
    @PostMapping("/headerImgUpload")
    public Result<Object> headerImgUpload(@ApiParam("封装请求体中的图片二进制数据") @RequestPart("multipartFile") MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        assert originalFilename != null;
        String photoName = UUID.randomUUID().toString().replace("-", "").toLowerCase().
                concat(originalFilename.substring(originalFilename.lastIndexOf(".")));
        String savePath = "D:/JavaProject/smart_campus/module_campus/src/main/resources/static/upload/".concat(photoName);
        //保存图片
        multipartFile.transferTo(new File(savePath));
        return Result.ok("upload/".concat(photoName));
    }

    @ApiOperation("修改用户密码功能")
    @PostMapping("updatePwd/{oldPwd}/{newPwd}")
    public Result<Object> updatePwd(@ApiParam("请求头中的token数据") @RequestHeader("token") String token,
                                    @ApiParam("路径参数中的原密码") @PathVariable("oldPwd") String oldPwd,
                                    @ApiParam("路径参数中的新密码") @PathVariable("newPwd") String newPwd) {
        //先判断一下token是否过期
        if (JwtHelper.isExpiration(token)) {
            return Result.fail().message("token失效,请重新登录后修改");
        }
        //获取用户id
        Long userId = JwtHelper.getUserId(token);
        //根据token 判断用户类型
        Integer userType = JwtHelper.getUserType(token);
        assert userType != null;
        if(userType == 1){
            //对旧密码进行校验
            Admin admin = adminService.selectAdminById(userId);
            if(!MD5.encrypt(oldPwd).equals(admin.getPassword())){
                return Result.fail().message("原密码输入有误");
            }
            //原密码输入正确 就将新密码进行加密后进行修改
            admin.setPassword(MD5.encrypt(newPwd));
            adminService.update(admin,new LambdaQueryWrapper<Admin>().eq(Admin::getId,userId));
        }else if(userType == 2){
            Student student = studentService.selectAdminById(userId);
            if(!MD5.encrypt(oldPwd).equals(student.getPassword())){
                return Result.fail().message("原密码输入有误");
            }
            student.setPassword(MD5.encrypt(newPwd));
            studentService.update(student,new LambdaQueryWrapper<Student>().eq(Student::getId,userId));
        }else {
            Teacher teacher = teacherService.selectAdminById(userId);
            if(!MD5.encrypt(oldPwd).equals(teacher.getPassword())){
                return Result.fail().message("原密码输入有误");
            }
            teacher.setPassword(MD5.encrypt(newPwd));
            teacherService.update(teacher,new LambdaQueryWrapper<Teacher>().eq(Teacher::getId,userId));
        }
        return Result.ok();
    }


}
