package com.sky.simple.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 管理员dao
 */

@Mapper
public interface AdminMapper {

    /**
     * 验证密码
     */
    @Select("Select AdminId from admin where AdminName = #{username} and AdminPassword = #{password} ")
    public Long verifyPassword(String username,String password);


}
