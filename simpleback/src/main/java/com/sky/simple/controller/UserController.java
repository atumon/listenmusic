package com.sky.simple.controller;

import com.alibaba.fastjson.JSONObject;
import com.sky.simple.domain.SongList;
import com.sky.simple.domain.User;
import com.sky.simple.domain.UserToken;
import com.sky.simple.service.SongListService;
import com.sky.simple.service.UserService;
import com.sky.simple.utils.Consts;

import com.sky.simple.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.events.Event;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    private SongListService songListService;


    /**
     * 添加用户
     * @param request
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addUser(HttpServletRequest request){

        JSONObject jsonObject = new JSONObject();

        String username = request.getParameter("username").trim();
        System.out.println("开始添加用户："+ username);
        String password = request.getParameter("password").trim();
        String phoneNum = request.getParameter("phoneNum").trim();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhoneNum(phoneNum);
        user.setCreateTime(df.format(new Date()));

        if(userService.selectByUserName(username) != null){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名重复");
            return jsonObject;
        }
        if(userService.selectByPhoneNum(phoneNum) != null){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"电话重复");
            return jsonObject;
        }

        Boolean flag = userService.insert(user);

        if (flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"注册成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"注册失败");

        return jsonObject;
    }

    /**
     * 用户注册
     * @param request
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Object register(HttpServletRequest request){

        JSONObject jsonObject = new JSONObject();

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String phoneNum = request.getParameter("phoneNum").trim();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhoneNum(phoneNum);
        user.setAvatar("/img/userAvatar/uuu.png");
        user.setCreateTime(df.format(new Date()));

        if(userService.selectByUserName(username) != null){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名重复");
            return jsonObject;
        }
        if(userService.selectByPhoneNum(phoneNum) != null){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"电话重复");
            return jsonObject;
        }

        Boolean flag = userService.insert(user);

        User userReturn = userService.selectByUserName(username);

        UserToken userToken = new UserToken(userReturn.getId(),userReturn.getUsername());

        if (flag){
            try {
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"注册成功");
                String token = TokenUtils.generateToken(userToken,0);
                jsonObject.put(Consts.TOKEN,token);
                return jsonObject;
            } catch (Exception e) {
                jsonObject.put(Consts.CODE,3);
                jsonObject.put(Consts.MSG,"注册成功");
                return jsonObject;
            }
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"注册失败");

        return jsonObject;
    }

    /**
     * 登录验证
     */
    @RequestMapping(value = "/signIn",method = RequestMethod.POST)
    public Object signIn(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        Long userId = userService.verifyPassword(username,password);

        if (userId != null){
            try {
                UserToken userToken = new UserToken();
                userToken.setId(userId);
                userToken.setName(username);
                String token = TokenUtils.generateToken(userToken,0);

                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"登录成功");
                jsonObject.put("token",token);

            }catch (Exception e){
                jsonObject.put(Consts.CODE,3);
                jsonObject.put(Consts.MSG,"服务器出现问题");
            }
        } else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名或密码错误");
        }
        return jsonObject;
    }

    /**
     * 更新用户头像
     */
    @RequestMapping(value = "/updateUserPic",method = RequestMethod.POST)
    public Object updateUserPic(@RequestParam("file")MultipartFile avatarFile, @RequestParam("id")Long id){

        JSONObject jsonObject = new JSONObject();

        if (avatarFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"没有上传");
            return jsonObject;
        }

        //文件名长时间字符串+原文件名
        String fileName = System.currentTimeMillis()+avatarFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")
                +System.getProperty("file.separator")+ "img"
                + System.getProperty("file.separator") + "userAvatar";
        //如果文件路径不存在 则添加路径

        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File desk = new File(filePath + System.getProperty("file.separator") + fileName);
        //相对文件地址（存储到数据库中）
        String storeAvatarFile = "/img/userAvatar/"+fileName;

        User user = userService.selectByPrimaryKey(id);

        if(!user.getAvatar().equals("img/userPic/uuu.png")){
            File file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + user.getAvatar());
            FileSystemUtils.deleteRecursively(file);
        }

        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
        user.setUpdateTime(df.format(new Date()));

        try {
            avatarFile.transferTo(desk);
            user.setAvatar(storeAvatarFile);
            Boolean flag = userService.update(user);

            if (flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("avatar",storeAvatarFile);
                return jsonObject;
            }else {
                jsonObject.put(Consts.CODE, 0);
                jsonObject.put(Consts.MSG, "修改失败");
            }
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,e.getMessage());

        }finally {
            return jsonObject;
        }
    }
    /**
     * 通过Token 查询用户信息
     */
    @RequestMapping(value = "/selectByToken",method = RequestMethod.POST)
    public Object selectByUserId(HttpServletRequest request) throws Exception {

        String token = request.getParameter("Token").trim();

        TokenUtils TokenUtils = new TokenUtils();

        UserToken userToken = TokenUtils.getInfoFromToken(token);

        return userService.selectByPrimaryKey(userToken.getId());
    }
    /**
     * 通过用户姓名 模糊查询
     */
    @RequestMapping(value = "/selectByUserName",method = RequestMethod.GET)
    public Object selectByUserName(@RequestParam("username")String username) throws Exception {
        return userService.selectByUserName(username);
    }
    /**
     * 查询全部用户
     */
    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)
    public Object selectAllSinger(HttpServletRequest request) throws Exception {
        return userService.selectAllUser();
    }
    /**
     * 修改用户信息
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateUser(HttpServletRequest request) throws Exception {

        JSONObject jsonObject = new JSONObject();
        Long id = Long.parseLong(request.getParameter("id").trim());

        User user = userService.selectByPrimaryKey(id);

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String phoneNum = request.getParameter("phoneNum").trim();

        String email = request.getParameter("email").trim();
        String sex = request.getParameter("sex").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String description = request.getParameter("description").trim();

        User searchNameUser = userService.selectByUserName(username);

        if (searchNameUser != null) {
            if (!searchNameUser.getId().equals(id)) {
                jsonObject.put(Consts.CODE, 0);
                jsonObject.put(Consts.MSG, "用户名重复");
                return jsonObject;
            }
        }

        User searchPhoneUser = userService.selectByPhoneNum(phoneNum);

        if (searchPhoneUser != null && !searchPhoneUser.getId().equals(id)) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "电话重复");
            return jsonObject;
        }


        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
        user.setUpdateTime(df.format(new Date()));


        user.setUsername(username);
        user.setPassword(password);
        user.setPhoneNum(phoneNum);
        user.setEmail(email);
        user.setBirth(birth);
        user.setDescription(description);
        try{
            user.setSex(Integer.parseInt(sex));
        }catch (Exception e){
            user.setSex(null);
        }
        user.setLocation(location);
        Boolean flag = userService.update(user);

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
     * 删除用户
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteUser(@RequestParam("id")Long id) throws Exception {

        User user = userService.selectByPrimaryKey(id);

        if(!user.getAvatar().equals("/img/userAvatar/uuu.png")){
            File file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + user.getAvatar());
            FileSystemUtils.deleteRecursively(file);
        }

        return userService.delete(id);
    }

    /**
     * 获取推荐
     */
    @RequestMapping(value = "/getRecommendation",method = RequestMethod.POST)
    public Object getRecommendation(HttpServletRequest request) throws Exception {

        String Token = request.getParameter("Token").trim();

        TokenUtils TokenUtils = new TokenUtils();

        UserToken userToken = TokenUtils.getInfoFromToken(Token);

        //推荐系统

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("123","hello");

        return jsonObject;
    }
    /**
     * 获取热门
     */
    @RequestMapping(value = "/getHot",method = RequestMethod.GET)
    public Object getHot() throws Exception {
        JSONObject jsonObject = new JSONObject();

        java.util.List hotList = songListService.selectHotList();

        return jsonObject;
    }

    /**
     * 获取创建歌单
     */
    @RequestMapping(value = "/getCreatedList",method = RequestMethod.GET)
    public Object getCreatedList(@RequestParam("id")Long id) throws Exception {
        List<SongList> songList = userService.getCreatedList(id);
        SongList myLike = userService.getMyLike(id);
        songList.add(0,myLike);
        return songList;
    }
    /**
     * 获取用户收藏歌单
     */
    @RequestMapping(value = "/getCollectList",method = RequestMethod.GET)
    public Object getCollectList(@RequestParam("id")Long id) throws Exception {
        return userService.getCollectList(id);
    }

}
