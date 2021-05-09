package com.sky.simple.service;

/**
 * 管理员接口
 */
public interface AdminService {

    /**
     * 验证密码
     * @param username
     * @param password
     * @return
     */
    public Long verifyPassword(String username,String password);

}
