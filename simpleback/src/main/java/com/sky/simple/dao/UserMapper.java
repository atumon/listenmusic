package com.sky.simple.dao;

import com.sky.simple.domain.SongList;
import com.sky.simple.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 歌手dao
 */

@Mapper
public interface UserMapper {

    /**
     * 添加
     */
    @Insert({ "Insert INTO User (username,password,phone_num,create_time) " +
            "VALUES " +
            "(#{username},#{password},#{phoneNum},#{createTime})" })
    int insert(User user);
    /**
     * 修改
     */
    @Update("Update user set " +
            "username=#{username}" +
            ",password=#{password}" +
            ",phone_num=#{phoneNum}" +
            ",email=#{email}" +
            ",sex=#{sex}" +
            ",birth=#{birth}" +
            ",location=#{location}" +
            ",avatar=#{avatar}" +
            ",update_time=#{updateTime} where id = #{id}")
    int update(User user);
    /**
     *  删除
     */
    @Delete("Delete from user where id = #{userId}")
    int delete(Long userId);
    /**
     * 通过主键查
     */
    @Select("Select * from user where id = #{userId}")
    User selectByPrimaryKey(Long userId);
    /**
     * 查全部
     */
    @Select("Select * from user")
    List<User> selectAllUser();

    /**
     * 通过用户姓名 模糊查询
     */
    @Select("Select * from user Where username = #{username}")
    User selectByUserName(String username);

    /**
     * 通过用户电话 查询
     */
    @Select("Select * from user Where phone_num = #{phoneNum}")
    User selectByPhoneNum(String phoneNum);
    /**
     * 通过用户邮箱 查询
     */
    @Select("Select * from user Where email = #{email}")
    User selectByEmail(String email);

    /**
     * 验证登录
     */
    @Select("Select id from user Where username = #{username} and password = #{password}")
    Long verifyPassword(String username,String password);

    /**
     * 获取创建歌单
     */
    @Select("Select * from view_list Where creator_id = #{id} and my_like = 0")
    List<SongList> getCreatedList(Long id);

    /**
     * 获取收藏歌单
     */
    @Select("Select * from view_collectlist Where CollectUId = #{id}")
    List<SongList> getCollectList(Long id);

    /**
     * 获取我喜欢的歌（歌单）
     */
    @Select("Select * from view_list Where creator_id = #{id} and my_like = 1")
    SongList getMyLike(Long id);

}
