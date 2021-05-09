package com.sky.simple.service;

import com.sky.simple.domain.Song;

import java.util.List;

/**
 * 管理歌曲dao
 */
public interface SongInListService {

    /**
     * 增加
     */
    public Boolean insert(Long listId,Long songId);

    /**
     *  删除
     */
    public Boolean delete(Long listId,Long songId);

    /**
     * 通过歌单ID查
     */
    public List<Song> selectByListId(Long id);

    /**
     * 通过歌单ID和歌曲ID查
     */
    public Song selectByListAndSongId(Long listId,Long songId);

}
