package com.atguigu.campus.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.KeyStore;

/**
 * ClassName: LoginForm
 * Package: com.atguigu.campus.pojo
 * Description:
 *
 * @author ziqiu
 * @Create: 2023/2/4 - 16:30  16:30
 * @Version: v1.0
 */

/**
 * 将登录的信息封装到该类 便于获取
 * @author ziqiu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {
    private String username;
    private String password;
    private String verifiCode;
    private Integer userType;
}
