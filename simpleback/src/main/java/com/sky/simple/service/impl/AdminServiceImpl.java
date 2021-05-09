package com.sky.simple.service.impl;

import com.sky.simple.dao.AdminMapper;
import com.sky.simple.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理员service实现类
 *
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    /**
     * 验证密码
     *
     * @param username
     * @param password
     */
    @Override
    public Long verifyPassword(String username, String password) {
        return adminMapper.verifyPassword(username, password);
    }
}
