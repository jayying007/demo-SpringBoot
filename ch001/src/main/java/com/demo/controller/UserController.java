package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhuangJieYing
 * @date 2021/3/20
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public String register(@RequestBody User userParam) {
        return userService.register(userParam);
    }

    @PostMapping("login")
    public String login(@RequestBody User userParam) {
        return userService.login(userParam.getUsername(), userParam.getPassword());
    }



    @PreAuthorize("hasAuthority('pms:read')")
    @GetMapping("permission/test1")
    public String testRead() {
        return "拥有pms:read的权限";
    }

    @PreAuthorize("hasAuthority('pms:write')")
    @GetMapping("permission/test2")
    public String testWrite() {
        return "拥有pms:write的权限";
    }

    @GetMapping("permission/test3")
    public String testOther() {
        return "可访问其他接口";
    }
}
