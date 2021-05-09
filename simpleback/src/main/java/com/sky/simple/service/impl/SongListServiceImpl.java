package com.sky.simple.service.impl;

import com.sky.simple.dao.ListMapper;
import com.sky.simple.domain.SongList;
import com.sky.simple.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongListServiceImpl implements SongListService {


    @Autowired
    ListMapper listMapper;

    /**
     * 增加
     *
     * @param songList
     */
    @Override
    public Boolean insert(SongList songList) {
        return listMapper.insert(songList)>=1;
    }

    /**
     * 修改
     *
     * @param songList
     */
    @Override
    public Boolean update(SongList songList) {
        return listMapper.update(songList)>=1;
    }

    /**
     * 修改
     *
     * @param songList
     */
    @Override
    public Boolean updateListInfo(SongList songList) {
        return listMapper.updateListInfo(songList) > 0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public Boolean delete(Long id) {
        return listMapper.delete(id)>=1;
    }

    /**
     * 通过主键查
     *
     * @param id
     */
    @Override
    public SongList selectByPrimaryKey(Long id) {
        return listMapper.selectByPrimaryKey(id);
    }

    /**
     * 查全部
     */
    @Override
    public List<SongList> selectAll() {
        return listMapper.selectAllSongList();
    }

    /**
     * 通过类型
     *
     * @param tag
     */
    @Override
    public List<SongList> selectByTag1(String tag) {
        return listMapper.selectByTag1(tag);
    }

    /**
     * 通过类型
     *
     * @param tag
     */
    @Override
    public List<SongList> selectByTag2(String tag) {
        return listMapper.selectByTag2(tag);

    }

    /**
     * 通过类型
     *
     * @param tag
     */
    @Override
    public List<SongList> selectByTag3(String tag) {
        return listMapper.selectByTag3(tag);
    }


    /**
     * 通过歌单名称查询
     *
     * @param singerName
     */
    @Override
    public List<SongList> selectByListName(String singerName) {
        return listMapper.selectByListName(singerName);
    }

    /**
     * 通过歌曲名 模糊查询
     *
     * @param id
     */
    @Override
    public List<SongList> selectByCreatorId(Long id) {
        return listMapper.selectByCreatorId(id);
    }

    /**
     * 查询热门歌单
     *
     */
    @Override
    public List<SongList> selectHotList() {
        return listMapper.selectHotList();
    }

    /**
     * 创建歌单
     *
     */
    @Override
    public int createList(SongList songList) {
        return listMapper.createList(songList);
    }

    /**
     * 查询热门歌单
     *
     * @param usrId
     * @param listId
     */
    @Override
    public Boolean selectListCollection(Long usrId, Long listId) {
        return listMapper.selectListCollection(usrId,listId) > 0;
    }

    /**
     * 收藏歌单
     *
     * @param userId
     * @param listId
     */
    @Override
    public Boolean collectList(Long userId, Long listId) {
        return listMapper.collectList(userId,listId) > 0;
    }

    /**
     * 收藏歌单
     *
     * @param listId
     */
    @Override
    public Boolean heatList(Long listId) {
        return listMapper.heatList(listId) >0;
    }

    /**
     * 收藏歌单
     *
     * @param listId
     * @param userId
     */
    @Override
    public Boolean cancelCollect(Long listId, Long userId) {
        return listMapper.cancelCollect(userId,listId) > 0;
    }


}
