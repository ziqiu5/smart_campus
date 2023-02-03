package com.atguigu.campus.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: SpringMvcConfigure
 * Package: com.atgiugu.campus.configure
 * Description:
 *
 * @author ziqiu
 * @Create: 2023/2/3 - 20:37  20:37
 * @Version: v1.0
 */

@Configuration
public class SpringMvcConfigure implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
    }
}
