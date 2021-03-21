package com.demo.entity;

import lombok.Data;

/**
 * @author ZhuangJieYing
 * @date 2021/3/20
 */
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private Integer status;
}
