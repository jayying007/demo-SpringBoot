package com.demo.config;

import com.demo.entity.Permission;
import com.demo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZhuangJieYing
 * @date 2021/3/21
 */
public class MyUserDetails implements UserDetails {
    private User user;
    private List<Permission> permissionList;

    public MyUserDetails(User user, List<Permission> permissionList) {
        this.user = user;
        this.permissionList = permissionList;
    }

    /**
     *
     * @return 返回用户的权限, 根据 permission.value来判断
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissionList.stream()
                .filter(permission -> permission.getValue() != null)
                .map(permission ->new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getStatus() == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getStatus() == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getStatus() == 1;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus() == 1;
    }
}
