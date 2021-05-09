package com.sky.simple.service;

import com.sky.simple.domain.SongList;

import java.util.List;


/**
 * 管理歌曲dao
 */
public interface SongListService {

    /**
     * 增加
     */
    Boolean insert(SongList songList);
    /**
     * 修改
     */
    Boolean update(SongList songList);
    /**
     * 修改
     */
    Boolean updateListInfo(SongList songList);
    /**
     *  删除
     */
    Boolean delete(Long id);
    /**
     * 通过主键查
     */
    SongList selectByPrimaryKey(Long id);
    /**
     * 查全部歌单
     */
    List<SongList> selectAll();

    /**
     * 通过类型
     */
    List<SongList> selectByTag1(String tag);
    /**
     * 通过类型
     */
    List<SongList> selectByTag2(String tag);
    /**
     * 通过类型
     */
    List<SongList> selectByTag3(String tag);

    /**
     * 通过歌单名称查询
     */
    List<SongList> selectByListName(String singerName);
    /**
     * 通过创建者id 查询
     */
    List<SongList> selectByCreatorId(Long id);

    /**
     * 查询热门歌单
     */
    List<SongList> selectHotList();

    /**
     * 创建歌单
     *
     */
    int createList(SongList songList);

    /**
     * 查询是否收藏了此歌曲
     */
    Boolean selectListCollection(Long userId,Long listId);

    /**
     * 收藏歌单
     */
    Boolean collectList(Long usrId,Long listId);
    /**
     * 收藏歌单
     */
    Boolean heatList(Long listId);

    /**
     * 收藏歌单
     */
    Boolean cancelCollect(Long listId,Long userId);


}
