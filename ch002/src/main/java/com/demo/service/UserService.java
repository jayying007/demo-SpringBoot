package com.demo.service;

import com.demo.dao.UserDao;
import com.demo.dao.UserRoleRelationDao;
import com.demo.entity.Permission;
import com.demo.entity.User;
import com.demo.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZhuangJieYing
 * @date 2021/3/20
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleRelationDao userRoleRelationDao;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 根据用户名查询用户信息
     */
    public User getUserByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        List<User> userList = userDao.selectByExample(user);
        if(userList != null && !userList.isEmpty()) {
            return userList.get(0);
        }
        return null;
    }

    /**
     * 注册功能
     */
    public String register(User user) {
        user.setStatus(1);
        //查询是否有相同用户名的用户
        List<User> umsAdminList = userDao.selectByExample(user);
        if (umsAdminList.size() > 0) {
            return "fail";
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userDao.insert(user);

        return "success";
    }

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 是否成功
     */
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = JwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    public List<Permission> getPermissionList(Long userId) {
        return userRoleRelationDao.getPermissionList(userId);
    }
}
