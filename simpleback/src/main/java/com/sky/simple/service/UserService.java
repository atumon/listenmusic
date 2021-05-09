package com.sky.simple.service;

import com.sky.simple.domain.SongList;
import com.sky.simple.domain.User;

import java.util.List;

public interface UserService {
    /**
     * 添加
     */
    public Boolean insert(User user);
    /**
     * 修改
     */
    public Boolean update(User user);
    /**
     *  删除
     */
    public Boolean delete(Long userId);
    /**
     * 通过主键查
     */
    public User selectByPrimaryKey(Long userId);
    /**
     * 查全部
     */
    public List<User> selectAllUser();

    /**
     * 通过用户姓名 模糊查询
     */
    public User selectByUserName(String username);

    /**
     * 通过用户电话 查询
     */
    public User selectByPhoneNum(String phoneNum);
    /**
     * 通过用户邮箱 查询
     */
    public User selectByEmail(String email);

    /**
     * 验证登录
     */
    Long verifyPassword(String username,String password);

    /**
     * 获取用户收藏
     */
    List<SongList> getCollectList(Long id);

    /**
     * 获取用户创建歌单
     */
   List<SongList> getCreatedList(Long id);

    /**
     * 获取用户创建歌单
     */
    SongList getMyLike(Long id);
}
