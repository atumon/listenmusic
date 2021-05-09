package com.sky.simple.service.impl;


import com.sky.simple.dao.SingerMapper;
import com.sky.simple.domain.Singer;
import com.sky.simple.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌手service实现类
 *
 */
@Service
public class SingerServiceImpl implements SingerService {


    @Autowired
    private SingerMapper singerMapper;

    /**
     * 增加
     *
     * @param singer
     */
    @Override
    public Boolean insert(Singer singer) {

        return singerMapper.insert(singer)>0;

    }

    /**
     * 修改
     *
     * @param singer
     */
    @Override
    public Boolean update(Singer singer) {
        return singerMapper.update(singer)>0;
    }

    /**
     * 删除
     *
     * @param SingerId
     */
    @Override
    public Boolean delete(Long SingerId) {
        return singerMapper.delete(SingerId)>0;
    }

    /**
     * 通过主键查
     *
     * @param SingerId
     */
    @Override
    public Singer selectByPrimaryKey(Long SingerId){
        return singerMapper.selectByPrimaryKey(SingerId);
    }

    /**
     * 查全部
     *
     */
    @Override
    public List<Singer> selectAll() {
        return singerMapper.selectAllSinger();
    }

    /**
     * 通过歌手姓名 模糊查询
     *
     * @param SingerName
     */
    @Override
    public List<Singer> selectBySingerName(String SingerName) {
        return singerMapper.selectBySingerName(SingerName);
    }

    /**
     * 通过歌手性别查询
     *
     * @param SingerSex
     */
    @Override
    public List<Singer> selectBySingerSex(Byte SingerSex) {
        return singerMapper.selectBySingerSex(SingerSex);
    }

    /**
     * 通过歌手地区和性别 单个地区
     *
     * @param local
     * @param sex
     */
    @Override
    public List<Singer> selectBySingerLocal(String local, Byte sex) {
        return singerMapper.selectBySingerLocal(local,sex);
    }

    /**
     * 通过歌手地区和性别 多个地区
     *
     * @param local1
     * @param local2
     * @param sex
     */
    @Override
    public List<Singer> selectBySingerDLocal(String local1, String local2, Byte sex) {
        return singerMapper.selectBySingerDLocal(local1,local2,sex);
    }

    /**
     * 收藏歌手
     *
     * @param singerId
     * @param userId
     */
    @Override
    public Boolean collectSinger(Long singerId, Long userId) {
        return singerMapper.collectSinger(singerId,userId) > 0;
    }

    /**
     * 收藏歌手
     *
     * @param singerId
     * @param userId
     */
    @Override
    public Boolean selectSingerCollection(Long singerId, Long userId) {
        return singerMapper.selectSingerCollection(singerId,userId) > 0;
    }
}
