package com.atguigu.campus.configure;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: MybtisPlusConfigure
 * Package: com.atgiugu.campus.configure
 * Description:
 *
 * @author ziqiu
 * @Create: 2023/2/3 - 20:35  20:35
 * @Version: v1.0
 */
@MapperScan("com.atguigu.campus.mapper")
@Configuration
public class MybatisPlusConfigure {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor plusInterceptor = new MybatisPlusInterceptor();
        plusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return plusInterceptor;
    }

}
