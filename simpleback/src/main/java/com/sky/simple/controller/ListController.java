package com.sky.simple.controller;

import com.alibaba.fastjson.JSONObject;
import com.sky.simple.domain.SongList;
import com.sky.simple.service.SongListService;
import com.sky.simple.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.events.Event;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("songList")
public class ListController {

    @Autowired
    private SongListService songListService;

    //新建歌单
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSongList(HttpServletRequest request){

        JSONObject jsonObject = new JSONObject();

        String name = request.getParameter("name").trim();
        System.out.println("开始添加歌单："+ name);
        String creatorId = request.getParameter("creatorId").trim();

        String pic = request.getParameter("pic").trim();
        String share = request.getParameter("share").trim();
        String tag1 = request.getParameter("tag1").trim();
        String tag2 = request.getParameter("tag2").trim();
        String tag3 = request.getParameter("tag3").trim();
        String description = request.getParameter("description").trim();

        SongList songList = new SongList();
        songList.setCreatorId(Long.parseLong(creatorId));
        songList.setPic(pic);
        songList.setName(name);
        songList.setShare(Integer.parseInt(share));
        songList.setTag1(tag1);
        songList.setTag2(tag2);
        songList.setTag3(tag3);
        songList.setDescription(description);

        Boolean flag = songListService.insert(songList);

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
     * 更新歌单图片
     */
    @RequestMapping(value = "/updateSongListPic",method = RequestMethod.POST)
    public Object updateSongListPic(@RequestParam("file")MultipartFile avatorFile, @RequestParam("id")Long id){

        JSONObject jsonObject = new JSONObject();

        if (avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"修改失败");
            return jsonObject;
        }

        //文件名长时间字符串+原文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")
                +System.getProperty("file.separator")+ "img"
                + System.getProperty("file.separator") + "songListPic";
        //如果文件路径不存在 则添加路径

        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File desk = new File(filePath + System.getProperty("file.separator") + fileName);
        //相对文件地址（存储到数据库中）
        String storeAvatorPath = "/img/songListPic/"+fileName;

        SongList songList = songListService.selectByPrimaryKey(id);

        if(!songList.getPic().equals("img/songListPic/歌单.png")){
            File file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + songList.getPic());
            FileSystemUtils.deleteRecursively(file);
        }

        try {
            avatorFile.transferTo(desk);
            songList.setPic(storeAvatorPath);
            Boolean flag = songListService.update(songList);

            if (flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("pic",storeAvatorPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"修改失败");
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,e.getMessage());
        }finally {
            return jsonObject;
        }
    }
    /**
     * 根据歌单id查询
     */
    @RequestMapping(value = "/selectBySongListId",method = RequestMethod.GET)
    public Object selectBySongListId(@RequestParam("id")Long listId) throws Exception {
        return songListService.selectByPrimaryKey(listId);
    }
    /**
     * 根据歌单名查询
     */
    @RequestMapping(value = "/selectBySongListName",method = RequestMethod.GET)
    public Object selectBySongListName(@RequestParam("songListName")String songListName) throws Exception {

        return songListService.selectByListName("%"+songListName+"%");
    }
    /**
     * 查询全部歌曲
     */
    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)
    public Object selectAllSongList(HttpServletRequest request) throws Exception {

        return songListService.selectAll();

    }
    /**
     * 根据歌单类型
     */
    @RequestMapping(value = "/selectByTag",method = RequestMethod.GET)
    public Object selectByTag(@RequestParam("tag")String tag) throws Exception {

        List<SongList> songList1 = songListService.selectByTag1(tag);
        List<SongList> songList2 = songListService.selectByTag2(tag);
        List<SongList> songList3 = songListService.selectByTag3(tag);

        List<SongList> songLists = songList1;
        songLists.addAll(songList2);
        songLists.addAll(songList3);

        //经过排序
        //由推荐系统排序

        return songLists;
    }

    /**
     * 根据歌单创建者id查询
     */
    @RequestMapping(value = "/selectSingerAllSongList",method = RequestMethod.GET)
    public Object selectSingerAllSongList(@RequestParam("creatorId")Long creatorId) throws Exception {

        return songListService.selectByCreatorId(creatorId);

    }
    /**
     * 修改歌曲
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSongList(HttpServletRequest request) throws Exception {

        JSONObject jsonObject = new JSONObject();
        Long id = Long.parseLong(request.getParameter("id").trim());

        SongList songList = songListService.selectByPrimaryKey(id);

        String name = request.getParameter("name").trim();
        String share = request.getParameter("share").trim();
        String tag1 = request.getParameter("tag1").trim();
        String tag2 = request.getParameter("tag2").trim();
        String tag3 = request.getParameter("tag3").trim();
        String description = request.getParameter("description").trim();

        songList.setName(name);
        songList.setShare(Integer.parseInt(share));
        songList.setTag1(tag1);
        songList.setTag2(tag2);
        songList.setTag3(tag3);
        songList.setDescription(description);

        Boolean flag = songListService.update(songList);

        if (flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"修改失败");
            return jsonObject;
        }
    }
    /**
     * 删除歌单
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteSongList(@RequestParam("id")Long id) throws Exception {

        SongList songList = songListService.selectByPrimaryKey(id);

        if(songList.getPic() == null || songList.getPic().equals("/img/songListPic/歌单.png")||songList.getPic().equals("/img/songListPic/listPic.png")){
            return songListService.delete(id);
        }else {
            File file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + songList.getPic());
            FileSystemUtils.deleteRecursively(file);
            return  songListService.delete(id);
        }
    }
    /**
     * 删除歌单
     */
    @RequestMapping(value = "/cancelCollect",method = RequestMethod.GET)
    public Object cancelCollect(@RequestParam("userId")Long userId,@RequestParam("listId")Long listId) throws Exception {
        return songListService.cancelCollect(listId,userId);
    }
    /**
     * 创建歌单简单
     */
    @RequestMapping(value = "/createList",method = RequestMethod.GET)
    public Object createList(@RequestParam("listName")String listName,@RequestParam("creatorId")Long creatorId)throws Exception {
        JSONObject jsonObject = new JSONObject();
        SongList songList = new SongList();
        songList.setName(listName);
        songList.setCreatorId(creatorId);
        if (songListService.createList(songList) > 0) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "创建成功");
            jsonObject.put("listId", songList.getId());
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "创建失败");
            return jsonObject;
        }
    }
    /**
     * 收藏歌单
     */
    @RequestMapping(value = "/collectList",method = RequestMethod.GET)
    public Object collectList(@RequestParam("listId")Long listId,@RequestParam("userId")Long userId)throws Exception {
        JSONObject jsonObject = new JSONObject();
        if (songListService.collectList(userId, listId)) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "收藏成功");
        }else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "服务器故障");
        }
        return jsonObject;
    }
    /**
     * 验证是否已经收藏
     */
    @RequestMapping(value = "/haveIt",method = RequestMethod.GET)
    public Object haveIt(@RequestParam("listId")Long listId,@RequestParam("userId")Long userId)throws Exception {
        if (songListService.selectListCollection(userId,listId)){
            return 1;
        } else {
            return 0;
        }
    }
    /**
     * 验证是否已经收藏
     */
    @RequestMapping(value = "/heatList",method = RequestMethod.GET)
    public Object heatList(@RequestParam("listId")Long listId)throws Exception {
        return songListService.heatList(listId);
    }
    /**updateListInfo
     * 更新歌单信息
     */
    @RequestMapping(value = "/updateListInfo",method = RequestMethod.POST)
    public Object updateListInfo(HttpServletRequest request)throws Exception {

        JSONObject jsonObject = new JSONObject();
        Long id = Long.parseLong(request.getParameter("id").trim());

        SongList songList = new SongList();
        String name = request.getParameter("name").trim();
        String tag1 = request.getParameter("tag1").trim();
        String tag2 = request.getParameter("tag2").trim();
        String tag3 = request.getParameter("tag3").trim();
        String description = request.getParameter("description").trim();

        songList.setId(id);

        songList.setName(name);
        if (tag1 != null) {
            songList.setTag1(tag1);
        }else {
            songList.setTag1("");
        }
        if (tag2 != null) {
            songList.setTag2(tag2);
        }else {
            songList.setTag2("");
        }
        if (tag3 != null) {
            songList.setTag3(tag3);
        }else {
            songList.setTag3("");
        }
        songList.setDescription(description);
        if (songListService.updateListInfo(songList)){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"保存成功");
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"保存失败");
        }

        return jsonObject;
    }

}
