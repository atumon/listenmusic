package com.sky.simple.dao;

import com.sky.simple.domain.Song;
import com.sky.simple.domain.SongList;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 歌单dao
 */
@Mapper
public interface ListMapper {

    /**
     * 增加
     */
    @Insert({"Insert INTO list (name,creator_id,pic,share,type,style,language,description) " +
            "VALUES " +
            "(#{name},#{creatorId},#{pic},#{share},#{type},#{style},#{language},#{description})" })
    int insert(SongList songList);
    /**
     * 修改
     */
    @Update("Update list set " +
            "name = #{name}" +
            ",creator_id=#{creatorId}" +
            ",pic=#{pic}" +
            ",share=#{share}" +
            ",tag1=#{tag1}" +
            ",tag2=#{tag2}" +
            ",tag3=#{tag3}" +
            ",description=#{description} where id = #{id}")
    int update(SongList songList);

    /**
     * 修改
     */
    @Update("Update list set " +
            "name = #{name}" +
            ",tag1=#{tag1}" +
            ",tag2=#{tag2}" +
            ",tag3=#{tag3}" +
            ",description=#{description} where id = #{id}")
    int updateListInfo(SongList songList);
    /**
     *  删除
     */
    @Delete("Delete from list where id = #{id}")
    int delete(Long id);
    /**
     * 通过歌单主键查询
     */
    @Select("select * from view_list where id = #{listId}")
    SongList selectByPrimaryKey(Long listId);
    /**
     * 查全部
     */
    @Select("Select * from view_list where my_like = 0")
    List<SongList> selectAllSongList();
    /**
     * 通过歌单名模糊查询      mark // 如果添加是否分享功能需要修改这里
     */
    @Select("Select * from view_list Where name like #{name}")
    List<SongList> selectByListName(String name);
    /**
     *根据创建者查询
     */
    @Select("Select * from view_list Where creator_id = #{creatorId}")
    List<SongList> selectByCreatorId(Long creatorId);

    /**
     *根据歌单tag1查询
     */
    @Select("Select * from view_list Where tag1 = #{tag}")
    List<SongList> selectByTag1(String tag);
    /**
     *根据歌单tag2查询
     */
    @Select("Select * from view_list Where tag2 = #{tag}")
    List<SongList> selectByTag2(String tag);
    /**
     *根据歌单tag3查询
     */
    @Select("Select * from view_list Where tag3 = #{tag}")
    List<SongList> selectByTag3(String tag);

    /**
     *根据歌单type和style查询
     */
    @Select("Select * from view_list Where type = #{type} and style = #{style}")
    List<SongList> selectByTypeAndStyle(String type);
    /**
     *根据歌单type和style和language查询
     */
    @Select("Select * from view_list Where type = #{type} and style = #{style} and style = #{style} and language = #{language}")
    List<SongList> selectByAllTaste(String type);

    @Select("Select * from view_hot_list")
    List<SongList> selectHotList();

    @Select("select * from List where name = '我喜欢的音乐' and creator_id = #{id}")
    SongList selectLikeList(Long id);

    @Insert({"Insert INTO list (name,creator_id) " +
            "VALUES " +
            "(#{name},#{creatorId})" })
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int createList(SongList songList);

    @Select("select count(*) from listcollection where CollectUId = #{userId} and CollectLId = #{listId}")
    int selectListCollection(Long userId,Long listId);

    @Insert("insert into listcollection (CollectUId,CollectLId)" +
            "values" +
            "(#{userId},#{listId})")
    int collectList(Long userId,Long listId);


    @Select("<script>"
            + "SELECT * FROM list WHERE id IN "
            + "<foreach item='item' index='index' collection='listIdList' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    List<SongList> queryMusicByIds(@Param("listIdList") List<Long> ids);

    /**
     * 修改
     */
    @Update("Update list set heat = heat + 1 where id = #{listId}")
    int heatList(Long listId);

    /**
     *  取消收藏
     */
    @Delete("Delete from listcollection where CollectUId = #{userId} and CollectLId = #{listId}")
    int cancelCollect(Long userId,Long listId);
}
