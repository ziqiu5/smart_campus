package com.atguigu.campus.configure;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: MybatispPlusConfigure
 * Package: com.atguigu.campus.confugure
 * Description:
 *
 * @author ziqiu
 * @Create: 2023/2/4 - 16:06  16:06
 * @Version: v1.0
 */

@Configuration
@MapperScan("com.atguigu.campus.mapper")
public class MybatisPlusConfigure {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        //添加 mybatis-plus中的分页插件
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return mybatisPlusInterceptor;
    }


}
