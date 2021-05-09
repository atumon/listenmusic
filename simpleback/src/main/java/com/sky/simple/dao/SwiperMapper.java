package com.sky.simple.dao;

import com.sky.simple.domain.Swiper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 轮播图dao
 */

@Mapper
public interface SwiperMapper {

    /**
     * 增加
     */
    @Insert({ "Insert INTO swiper (name,url) " +
            "VALUES " +
            "(#{name},#{url})" })
    int insert(Swiper swiper);
    /**
     * 修改
     */
    @Update("Update swiper set " +
            "name=#{name}" +
            ",url=#{url} where id = #{id}")
    int update(Swiper swiper);
    /**
     *  删除
     */
    @Delete("Delete from swiper where id = #{id}")
    int delete(Long id);

    /**
     *  获取全部数据
     */
    @Select("Select * from swiper")
    List<Swiper> getAll();

}
