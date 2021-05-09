package com.sky.simple.service.impl;

import com.sky.simple.dao.SwiperMapper;
import com.sky.simple.domain.Swiper;
import com.sky.simple.service.SwiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwiperServiceImpl implements SwiperService {


    @Autowired
    SwiperMapper swiperMapper;

    /**
     * 增加
     *
     * @param swiper
     */
    @Override
    public Boolean insert(Swiper swiper) {
        return swiperMapper.insert(swiper)>=1;
    }

    /**
     * 修改
     *
     * @param swiper
     */
    @Override
    public Boolean update(Swiper swiper) {
        return swiperMapper.update(swiper)>=1;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public Boolean delete(Long id) {
        return swiperMapper.delete(id)>=1;
    }


    /**
     * 查全部
     */
    @Override
    public List<Swiper> getAll() {
        return swiperMapper.getAll();
    }

}
