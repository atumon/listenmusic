package com.sky.simple.controller;

import com.alibaba.fastjson.JSONObject;
import com.sky.simple.domain.Song;
import com.sky.simple.service.SongService;
import com.sky.simple.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("song")
public class SongController {

    @Autowired
    private SongService songService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSong(HttpServletRequest request, @RequestParam("file")MultipartFile mpFile){

        JSONObject jsonObject = new JSONObject();

        String name = request.getParameter("name").trim();
        System.out.println("开始添加歌曲："+ name);
        String singerId = request.getParameter("singerId").trim();
        String pic = request.getParameter("pic").trim();
        String lyric = request.getParameter("lyric").trim();
        String album = request.getParameter("album").trim();

        //文件名长时间字符串+原文件名
        String fileName = System.currentTimeMillis()+mpFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")
                +System.getProperty("file.separator")+ "song";
        //如果文件路径不存在 则添加路径
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File desk = new File(filePath + System.getProperty("file.separator") + fileName);
        //相对文件地址（存储到数据库中）
        String storeAvatorPath = "/song/"+fileName;
        try {
            mpFile.transferTo(desk);
            Song song = new Song();
            song.setSingerId(Long.parseLong(singerId));
            song.setName(name);
            song.setSingerId(Long.parseLong(singerId));
            song.setPic(pic);
            song.setAlbum(album);
            song.setLyric(lyric);
            song.setUrl(storeAvatorPath);
            Boolean flag = songService.insert(song);

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
            System.out.println(e.getMessage());
        }finally {
            return jsonObject;
        }
    }
    /**
     * 更新歌曲图片
     */
    @RequestMapping(value = "/updateSongPic",method = RequestMethod.POST)
    public Object updateSongPic(@RequestParam("file")MultipartFile avatorFile, @RequestParam("id")Long id){

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
                + System.getProperty("file.separator") + "songPic";
        //如果文件路径不存在 则添加路径

        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File desk = new File(filePath + System.getProperty("file.separator") + fileName);
        //相对文件地址（存储到数据库中）
        String storeAvatorPath = "/img/songPic/"+fileName;

        Song song = songService.selectByPrimaryKey(id);
        if(!song.getPic().equals("img/songPic/mmm.png")){
            File file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + song.getPic());
            FileSystemUtils.deleteRecursively(file);
        }

        try {
            avatorFile.transferTo(desk);
            song.setPic(storeAvatorPath);
            Boolean flag = songService.update(song);

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
     * 根据歌手名模糊查询
     */
    @RequestMapping(value = "/selectBySingerName",method = RequestMethod.GET)
    public Object selectBySingerName(@RequestParam("singerName")String singerName) throws Exception {
        return songService.selectBySingerName(singerName);
    }
    /**
     * 根据歌名模糊查询
     */
    @RequestMapping(value = "/selectBySongName",method = RequestMethod.GET)
    public Object selectBySongName(@RequestParam("songName")String songName) throws Exception {

        return songService.selectBySongName("%"+songName+"%");
    }
    /**
     * 查询全部歌曲
     */
    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)
    public Object selectAllSinger(HttpServletRequest request) throws Exception {

        return songService.selectAll();

    }
    /**
     * 查询歌手全部歌曲
     */
    @RequestMapping(value = "/selectSingerAllSong",method = RequestMethod.GET)
    public Object selectSingerAllSong(@RequestParam("id")Long id) throws Exception {

        return songService.selectBySingerId(id);

    }
    /**
     * 修改歌曲
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSong(HttpServletRequest request) throws Exception {

        JSONObject jsonObject = new JSONObject();
        Long id = Long.parseLong(request.getParameter("id").trim());

        Song song = songService.selectByPrimaryKey(id);

        String  name = request.getParameter("name").trim();
        String album = request.getParameter("album").trim();
        String lyric = request.getParameter("lyric").trim();

        song.setName(name);
        song.setAlbum(album);
        song.setLyric(lyric);

        Boolean flag = songService.update(song);

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
     * 删除歌曲
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteSong(@RequestParam("id")Long id) throws Exception {

        Song song = songService.selectByPrimaryKey(id);

        File file = new File(System.getProperty("user.dir")+System.getProperty("file.separator")+song.getUrl());

        Boolean flag = FileSystemUtils.deleteRecursively(file);

        return songService.delete(id);

    }
}
