package com.sky.simple.service.impl;

import com.sky.simple.dao.SongInListMapper;
import com.sky.simple.domain.Song;
import com.sky.simple.service.SongInListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongInListServiceImpl implements SongInListService {


    @Autowired
    SongInListMapper songInListMapper;


    /**
     * 增加
     *
     * @param listId
     * @param songId
     */
    @Override
    public Boolean insert(Long listId, Long songId) {
        return songInListMapper.insert(listId,songId)>0;
    }

    /**
     * 删除
     *
     * @param listId,songId
     */
    @Override
    public Boolean delete(Long listId,Long songId) {
        return songInListMapper.delete(listId,songId)>=1;
    }

    /**
     * 通过歌单ID查询歌单里的全部歌曲
     *
     * @param id
     */
    @Override
    public List<Song> selectByListId(Long id) {
        return songInListMapper.selectByListId(id);
    }

    /**
     * 通过歌单ID和歌曲ID查
     *
     * @param listId
     * @param songId
     */
    @Override
    public Song selectByListAndSongId(Long listId, Long songId) {
        return songInListMapper.selectByListAndSongId(listId,songId);
    }
}
