package com.sky.simple.service;

import com.sky.simple.domain.Singer;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 管理歌手dao
 */
public interface SingerService {

    /**
     * 增加
     */
    public Boolean insert(Singer singer);
    /**
     * 修改
     */
    public Boolean update(Singer singer);
    /**
     *  删除
     */
    public Boolean delete(Long SingerId);
    /**
     * 通过主键查
     */
    public Singer selectByPrimaryKey(Long SingerId);
    /**
     * 查全部
     */
    public List<Singer> selectAll();
    /**
     * 通过歌手姓名 模糊查询
     */
    public List<Singer> selectBySingerName(String SingerName);
    /**
     * 通过歌手性别查询
     */
    public List<Singer> selectBySingerSex(Byte SingerSex);

    /**
     * 通过歌手地区和性别 单个地区
     */
    List<Singer> selectBySingerLocal(String local,Byte sex);

    /**
     * 通过歌手地区和性别 多个地区
     */
    List<Singer> selectBySingerDLocal(String local1,String local2,Byte sex);
    /**
     * 收藏歌手
     */
    Boolean collectSinger(Long singerId,Long userId);
    /**
     * 收藏歌手
     */
    Boolean selectSingerCollection(Long singerId,Long userId);

}
