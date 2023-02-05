package com.atguigu.campus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName: TestContrloller
 * Package: com.atguigu.campus.controller
 * Description:
 *
 * @author ziqiu
 * @Create: 2023/2/5 - 20:35  20:35
 * @Version: v1.0
 */

@Controller
public class TestController {
    @RequestMapping("/deleteGrade")
    public String toDelete(){
        return "deleteForm";
    }

}
