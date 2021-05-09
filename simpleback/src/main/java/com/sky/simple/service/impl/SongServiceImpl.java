package com.sky.simple.service.impl;

import com.sky.simple.dao.SongMapper;
import com.sky.simple.domain.Song;
import com.sky.simple.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {


    @Autowired
    SongMapper songMapper;

    /**
     * 增加
     *
     * @param song
     */
    @Override
    public Boolean insert(Song song) {
        return songMapper.insert(song)>=1;
    }

    /**
     * 修改
     *
     * @param song
     */
    @Override
    public Boolean update(Song song) {
        return songMapper.update(song)>=1;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public Boolean delete(Long id) {
        return songMapper.delete(id)>=1;
    }

    /**
     * 通过主键查
     *
     * @param id
     */
    @Override
    public Song selectByPrimaryKey(Long id) {
        return songMapper.selectByPrimaryKey(id);
    }

    /**
     * 查全部
     */
    @Override
    public List<Song> selectAll() {
        return songMapper.selectAllSong();
    }

    /**
     * 通过歌手姓名查询
     *
     * @param singerName
     */
    @Override
    public List<Song> selectBySingerName(String singerName) {
        return songMapper.selectBySingerName(singerName);
    }

    /**
     * 通过歌曲名 模糊查询
     *
     * @param name
     */
    @Override
    public List<Song> selectBySongName(String name) {
        return songMapper.selectBySongName(name);
    }

    /**
     * 通过歌手ID查
     *
     * @param id
     */
    @Override
    public List<Song> selectBySingerId(Long id) {
        return songMapper.selectBySingerId(id);
    }
}
