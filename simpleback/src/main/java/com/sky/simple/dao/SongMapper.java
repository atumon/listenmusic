package com.sky.simple.dao;

import com.sky.simple.domain.Song;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 歌曲dao
 */

@Mapper
public interface SongMapper {

    /**
     * 增加
     */
    @Insert({ "Insert INTO Song (name,singer_id,pic,lyric,url,album) " +
            "VALUES " +
            "(#{name},#{singerId},#{pic},#{lyric},#{url},#{album})" })
    int insert(Song song);
    /**
     * 修改
     */
    @Update("Update Song set " +
            "name=#{name}" +
            ",pic=#{pic} " +
            ",lyric=#{lyric}" +
            ",url=#{url} where id = #{id}")
    int update(Song Song);
    /**
     *  删除
     */
    @Delete("Delete from Song where id = #{id}")
    int delete(Long id);
    /**
     * 通过主键查
     */
    @Select("Select * from song where id = #{id}")
    Song selectByPrimaryKey(Long id);
    /**
     * 查全部
     */
    @Select("Select * from view_allSong")
    List<Song> selectAllSong();
    /**
     * 通过歌手姓名模糊查询
     */
    @Select("Select * from song Where singer_name like #{singerName}")
    List<Song> selectBySingerName(String singerName);
    /**
     *根据歌曲名查询
     */
    @Select("Select * from song Where name like #{name} ")
    List<Song> selectBySongName(String name);
    /**
     *根据歌手ID查询
     */
    @Select("Select * from song Where singer_id = #{id}")
    List<Song> selectBySingerId(Long id);


}
