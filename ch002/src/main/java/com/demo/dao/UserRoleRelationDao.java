package com.demo.dao;

import com.demo.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZhuangJieYing
 * @date 2021/3/20
 */
@Repository
@Mapper
public interface UserRoleRelationDao {
    /**
     * 获取用户所有权限
     */
    List<Permission> getPermissionList(@Param("userId") Long userId);
}
