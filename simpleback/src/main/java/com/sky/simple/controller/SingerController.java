package com.sky.simple.controller;

import com.alibaba.fastjson.JSONObject;
import com.sky.simple.dao.SingerMapper;
import com.sky.simple.domain.Singer;
import com.sky.simple.service.SingerService;
import com.sky.simple.service.SongService;
import com.sky.simple.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    @Autowired
    private SongService songService;

    /**
     * 添加歌手
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSinger(HttpServletRequest request) throws Exception {

        JSONObject jsonObject = new JSONObject();

        String name = request.getParameter("name").trim();
        System.out.println("开始添加歌手："+ name);
        String sex = request.getParameter("sex").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String description = request.getParameter("description").trim();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        }catch (ParseException e){
            e.printStackTrace();
        }

        Singer singer = new Singer(name,new Integer(sex),birth,location,description);
        Boolean flag = singerService.insert(singer);

        if (flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"添加失败");
            return jsonObject;
        }
    }
    /**
     * 修改歌手
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSinger(HttpServletRequest request) throws Exception {

        JSONObject jsonObject = new JSONObject();
        Long id = Long.parseLong(request.getParameter("id").trim());

        Singer originSinger = singerService.selectByPrimaryKey(id);

        String name = request.getParameter("name").trim();
        String sex = request.getParameter("sex").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String description = request.getParameter("description").trim();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        }catch (ParseException e){
            e.printStackTrace();
        }

        Singer singer = new Singer(id,name,new Integer(sex),birth,location,description);
        singer.setPic(originSinger.getPic());

        Boolean flag = singerService.update(singer);

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
     * 删除歌手
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteeSinger(HttpServletRequest request) throws Exception {

        String id = request.getParameter("id").trim();

        return singerService.delete(Long.parseLong(id));

    }
    /**
     * 根据歌手id查询歌手
     */
    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(@RequestParam("id")Long id) throws Exception {
        return singerService.selectByPrimaryKey(id);
    }
    /**
     * 收藏歌手
     */
    @RequestMapping(value = "/collectSinger",method = RequestMethod.GET)
    public Object collectSinger(@RequestParam("singerId")Long singerId,@RequestParam("userId")Long userId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if (singerService.collectSinger(singerId,userId)){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"收藏成功");
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"收藏失败");
        }
        return jsonObject;
    }
    /**
     * 查询歌手TOP10  通过推荐系统（暂用）
     */
    @RequestMapping(value = "/selectSingerTop10",method = RequestMethod.GET)
    public Object selectSingerTop10(@RequestParam("id")Long id) throws Exception {
        return songService.selectBySingerId(id);
    }
    /**
     * 查询一个歌手
     */
    @RequestMapping(value = "/selectone",method = RequestMethod.GET)
    public Object selectSinger(HttpServletRequest request) throws Exception {

        String id = request.getParameter("id").trim();

        return singerService.selectByPrimaryKey(Long.parseLong(id));
    }

    /**
     * 查询全部歌手
     */
    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)
    public Object selectAllSinger(HttpServletRequest request) throws Exception {
        return singerService.selectAll();

    }

    /**
     * 通过地区和性别查询歌手
     */
    @RequestMapping(value = "/selectByLocalAndSex",method = RequestMethod.GET)
    public Object selectByLocalAndSex(@RequestParam("info") String info) throws Exception {

        String[] key = info.split(",");
        if (key.length == 2){
            return singerService.selectBySingerLocal(key[0],Byte.parseByte(key[1]));
        }else if (key.length == 3){
            return singerService.selectBySingerDLocal(key[0],key[1],Byte.parseByte(key[2]));
        }
        return new Singer();
    }
    /**
     * 根据歌手名模糊查询
     */
    @RequestMapping(value = "/selectBySingerName",method = RequestMethod.GET)
    public Object selectbysingername(@RequestParam("singerName")String SingerName) throws Exception {
        return singerService.selectBySingerName("%"+SingerName+"%");
    }

    /**
     * 根据歌手性别查询
     */
    @RequestMapping(value = "/singerofsex",method = RequestMethod.GET)
    public Object selectbysingersex(HttpServletRequest request) throws Exception {
        String sex = request.getParameter("sex").trim();
        return singerService.selectBySingerSex(Byte.parseByte(sex));
    }
    /**
     * 更新歌手图片
     */
    @RequestMapping(value = "/updateSingerPic",method = RequestMethod.POST)
    public Object updateSingerPic(@RequestParam("file")MultipartFile avatarFile, @RequestParam("id")Long id){
        JSONObject jsonObject = new JSONObject();
        if (avatarFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"修改失败");
            return jsonObject;
        }
        //文件名长时间字符串+原文件名
        String fileName = System.currentTimeMillis()+avatarFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")
                +System.getProperty("file.separator")+ "img"
                + System.getProperty("file.separator") + "singerPic";
        //如果文件路径不存在 则添加路径
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }

        //实际的文件地址
        File desk = new File(filePath + System.getProperty("file.separator") + fileName);
        //相对文件地址（存储到数据库中）
        String storeAvatarPath = "/img/singerPic/"+fileName;

        Singer singer = singerService.selectByPrimaryKey(id);
        if(singer.getPic() != null) {
            if (!singer.getPic().equals("img/singerPic/zzz.png")) {
                File file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + singer.getPic());
                FileSystemUtils.deleteRecursively(file);
            }
        }
        try {
            avatarFile.transferTo(desk);
//            singer.setSingerId(id);
            singer.setPic(storeAvatarPath);
            Boolean flag = singerService.update(singer);

            if (flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("pic",storeAvatarPath);
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
     * 验证是否已经收藏
     */
    @RequestMapping(value = "/haveIt",method = RequestMethod.GET)
    public Object haveIt(@RequestParam("singerId")Long singerId,@RequestParam("userId")Long userId)throws Exception {
        if (singerService.selectSingerCollection(singerId,userId)){
            return 1;
        } else {
            return 0;
        }
    }
}
