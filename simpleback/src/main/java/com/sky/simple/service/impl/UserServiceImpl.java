package com.sky.simple.service.impl;

import com.sky.simple.dao.UserMapper;
import com.sky.simple.domain.SongList;
import com.sky.simple.domain.User;
import com.sky.simple.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    /**
     * 添加
     *
     * @param user
     */
    @Override
    public Boolean insert(User user) {
        return userMapper.insert(user)>0;
    }

    /**
     * 修改
     *
     * @param user
     */
    @Override
    public Boolean update(User user) {
        return userMapper.update(user)>0;
    }

    /**
     * 删除
     *
     * @param userId
     */
    @Override
    public Boolean delete(Long userId) {
        return userMapper.delete(userId)>0;
    }

    /**
     * 通过主键查
     *
     * @param userId
     */
    @Override
    public User selectByPrimaryKey(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    /**
     * 查全部
     */
    @Override
    public java.util.List selectAllUser() {
        return userMapper.selectAllUser();
    }

    /**
     * 通过用户姓名 模糊查询
     *
     * @param username
     */
    @Override
    public User selectByUserName(String username) {
        return userMapper.selectByUserName(username);
    }

    /**
     * 通过用户电话 查询
     *
     * @param phoneNum
     */
    @Override
    public User selectByPhoneNum(String phoneNum) {
        return userMapper.selectByPhoneNum(phoneNum);
    }

    /**
     * 通过用户邮箱 查询
     *
     * @param email
     */
    @Override
    public User selectByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    /**
     * 验证登录
     *
     * @param username
     * @param password
     */
    @Override
    public Long verifyPassword(String username, String password) {
        return userMapper.verifyPassword(username,password);
    }

    /**
     * 获取用户收藏
     *
     * @param id
     */
    @Override
    public java.util.List getCollectList(Long id) {
        return userMapper.getCollectList(id);
    }

    /**
     * 获取用户创建歌单
     *
     * @param id
     */
    @Override
    public java.util.List getCreatedList(Long id) {
        return userMapper.getCreatedList(id);
    }

    /**
     * 获取用户创建歌单
     *
     * @param id
     */
    @Override
    public SongList getMyLike(Long id) {
        return userMapper.getMyLike(id);
    }
}
