package com.sky.simple.controller;

import com.alibaba.fastjson.JSONObject;
import com.sky.simple.dao.ListMapper;
import com.sky.simple.domain.Song;
import com.sky.simple.domain.SongList;
import com.sky.simple.service.SongInListService;
import com.sky.simple.service.SongListService;
import com.sky.simple.service.SongService;
import com.sky.simple.utils.Consts;
import com.sun.media.sound.RIFFInvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("songInList")
public class SongInListController {

    @Autowired
    private SongInListService songInListService;
    @Autowired
    private SongListService songListService;
    @Autowired
    private SongService songService;

    //新建歌单
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSongList(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();

        String listId = request.getParameter("listId").trim();
        String songId = request.getParameter("songId").trim();

        //判断数据库里是否已经存在
        Song inList = songInListService.selectByListAndSongId(Long.parseLong(listId),Long.parseLong(songId));

        if(inList != null){
            jsonObject.put(Consts.CODE,2);
            jsonObject.put(Consts.MSG,"你已经添加过了哟");
            return jsonObject;
        }

        Boolean flag = songInListService.insert(Long.parseLong(listId),Long.parseLong(songId));

        if (flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"上传成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;

    }

    /**
     * 根据歌单id查询
     */
    @RequestMapping(value = "/selectBySongListId",method = RequestMethod.GET)
    public Object selectBySongListId(@RequestParam("id")Long listId) {

        return songInListService.selectByListId(listId);

    }

    /**
     * 根据歌单id和歌曲id查询
     */
    @RequestMapping(value = "/selectByListAndSongId",method = RequestMethod.GET)
    public Object selectByListAndSongId(@RequestParam("listId")Long listId,@RequestParam("songId")Long songId){

        return songInListService.selectByListAndSongId(listId,songId);

    }

    /**
     * 删除歌单里的歌曲
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteSongList(@RequestParam("listId")Long listId,@RequestParam("songId")Long songId){
        return songInListService.delete(listId,songId);
    }

    /**
     * 收藏歌曲
     */
    @RequestMapping(value = "/collectSong",method = RequestMethod.GET)
    public Object collectSong(@RequestParam("listId")Long listId,@RequestParam("songId")Long songId){

        JSONObject jsonObject = new JSONObject();
        //判断数据库里是否已经存在
        Song inList = songInListService.selectByListAndSongId(listId,songId);

        if(inList != null){
            jsonObject.put(Consts.CODE,2);
            jsonObject.put(Consts.MSG,"当前歌曲已在播放列表中");
            return jsonObject;
        }

        SongList songList = songListService.selectByPrimaryKey(listId);

        if (songList.getAutoUpPic()){
            Song song = songService.selectByPrimaryKey(songId);
            songList.setPic(song.getPic());
            songListService.update(songList);
        }
        Boolean flag = songInListService.insert(listId,songId);
        if (flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "添加失败");
            return jsonObject;
        }
    }

}
