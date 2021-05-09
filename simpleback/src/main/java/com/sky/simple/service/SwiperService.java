package com.sky.simple.service;

import com.sky.simple.domain.Swiper;

import java.util.List;

/**
 * 管理歌曲dao
 */
public interface SwiperService {

    /**
     * 增加
     */
    public Boolean insert(Swiper swiper);
    /**
     * 修改
     */
    public Boolean update(Swiper swiper);
    /**
     *  删除
     */
    public Boolean delete(Long id);

    /**
     * 查全部
     */
    public List<Swiper> getAll();


}
