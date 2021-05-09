package com.sky.simple.dao;

import com.sky.simple.domain.Singer;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 歌手dao
 */

@Mapper
public interface SingerMapper {

    /**
     * 增加
     */
    @Insert({ "Insert INTO Singer (SingerName,SingerDescription, SingerSex,SingerPic,SingerBirth,SingerLocation) " +
            "VALUES " +
            "(#{SingerName},#{SingerDescription},#{SingerSex},#{SingerPic},#{SingerBirth},#{SingerLocation})" })
    public int insert(Singer singer);
    /**
     * 修改
     */
    @Update("Update Singer set " +
            "name=#{name}" +
            ",description=#{description}" +
            ",sex=#{sex}" +
            ",pic=#{pic}" +
            ",birth=#{birth}" +
            ",location=#{location} where id = #{id}")
    int update(Singer singer);
    /**
     *  删除
     */
    @Delete("Delete from singer where id = #{id}")
    public int delete(Long id);
    /**
     * 通过主键查
     */
    @Select("Select * from singer where id = #{id}")
    Singer selectByPrimaryKey(Long id);
    /**
     * 查全部
     */
    @Select("Select * from singer")
    List<Singer> selectAllSinger();
    /**
     * 通过歌手姓名 模糊查询
     */
    @Select("Select * from Singer Where name like #{name}")
    List<Singer> selectBySingerName(String name);
    /**
     * 通过歌手性别
     */
    @Select("Select * from Singer Where sex = #{sex}")
    List<Singer> selectBySingerSex(Byte sex);

    /**
     * 通过歌手地区和性别 多个地区
     */
    @Select("Select * from Singer Where location = #{local1} or location = #{local2} and sex = #{sex}")
    List<Singer> selectBySingerDLocal(String local1,String local2,Byte sex);

    /**
     * 通过歌手地区和性别 单个地区
     */
    @Select("Select * from Singer Where location = #{local} and sex = #{sex}")
    List<Singer> selectBySingerLocal(String local,Byte sex);

    /**
     * 收藏歌手
     */
    @Insert("Insert into singercollection (CollectSId,CollectUId)" +
            "values" +
            "(#{singerId},#{userId})")
    int collectSinger(Long singerId,Long userId);

    /**
     * 查询是否收藏
     */
    @Select("Select * from singercollection where CollectSId = #{singerId} and CollectUId=#{userId}")
    int selectSingerCollection(Long singerId,Long userId);

}