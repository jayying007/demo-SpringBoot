package com.demo.entity;

import lombok.Data;

/**
 * @author ZhuangJieYing
 * @date 2021/3/20
 */
@Data
public class Permission {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 权限的名称,前端展示
     */
    private String name;
    /**
     * 权限值,后端处理
     */
    private String value;
}
