package com.sky.simple.service;

import com.sky.simple.domain.Song;

import java.util.List;

/**
 * 管理歌曲dao
 */
public interface SongService {

    /**
     * 增加
     */
    public Boolean insert(Song song);
    /**
     * 修改
     */
    public Boolean update(Song song);
    /**
     *  删除
     */
    public Boolean delete(Long Id);
    /**
     * 通过主键查
     */
    public Song selectByPrimaryKey(Long id);
    /**
     * 查全部
     */
    public List<Song> selectAll();
    /**
     * 通过歌手姓名查询
     */
    List<Song> selectBySingerName(String singerName);
    /**
     * 通过歌曲名 模糊查询
     */
    public List<Song> selectBySongName(String name);
    /**
     * 通过歌手ID查
     */
    public List<Song> selectBySingerId(Long id);


}
