package com.atguigu.campus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author ziqiu
 * @TableName tb_clazz
 */
@TableName(value ="tb_clazz")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clazz implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Integer number;

    /**
     * 
     */
    private String introducation;

    /**
     * 
     */
    private String headmaster;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String telephone;

    /**
     * 
     */
    private String gradeName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}