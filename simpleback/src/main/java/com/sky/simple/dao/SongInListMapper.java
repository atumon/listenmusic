package com.sky.simple.dao;

import com.sky.simple.domain.Song;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * 歌单里的歌曲dao
 */

@Mapper
public interface SongInListMapper {

    /**
     * 增加
     */
    @Insert({"Insert INTO list_song (list_id,song_id) " +
            "VALUES " +
            "(#{listId},#{songId})" })
    int insert(Long listId,Long songId);

    /**
     *  删除
     */
    @Delete("Delete from list_song where list_id = #{listId} and song_id = #{songId}")
    int delete(Long listId,Long songId);

    /**
     * 通过歌单ID查
     */
    @Select("Select * from view_songinlist where list_id = #{id}")
    List<Song> selectByListId(Long id);

    /**
     * 通过歌单ID和歌曲ID查
     */
    @Select("Select * from view_songinlist where list_id = #{listId} and id = #{songId} ")
    Song selectByListAndSongId(Long listId,Long songId);


}
