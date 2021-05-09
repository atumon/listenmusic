package com.sky.simple.dao;

import com.sky.simple.domain.Score;
import com.sky.simple.domain.Song;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


/**
 * 评分dao
 */
@Mapper
public interface ScoreMapper {


    /**
     * 查询用户对全部标签的评分
     */
    @Select("select * from score where user_id = #{userId}")
    List<Score> selectAllScore(Long userId);

    /**
     * 查询用户对某标签的评分
     */
    @Select("select * from score where user_id = #{userId} and tag = #{tag}")
    List<Score> selectOneScore(Long userId,String tag);

    /**
     * 记录用户评分
     */
    @Update("Update score set " +
            "score=#{score}" +
            "where user_id = #{userId} and tag = #{tag}")
    int updateScore(Score score);

    /**
     * 插入新评分
     */
    @Insert({ "Insert INTO score (user_id,tag,score) " +
            "VALUES " +
            "(#{userId},#{tag},#{score})" })
    int insertScore(Score score);

}
