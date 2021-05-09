package com.sky.simple.controller;

import com.alibaba.fastjson.JSONObject;
import com.sky.simple.dao.SwiperMapper;
import com.sky.simple.domain.Song;
import com.sky.simple.domain.Swiper;
import com.sky.simple.service.SwiperService;
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

@RestController
@RequestMapping("swiper")
public class SwiperController {

    @Autowired
    SwiperService swiperService;



    @RequestMapping(value = "selectAll",method = RequestMethod.GET)
    public Object getSwiper(){
        return swiperService.getAll();
    }

    @RequestMapping(value = "insert",method = RequestMethod.GET)
    public Object insertSwiper(@RequestParam("file") MultipartFile swiperFile, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();

        String name = request.getParameter("name").trim();

        //文件名长时间字符串+原文件名
        String fileName = System.currentTimeMillis()+swiperFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")
                +System.getProperty("file.separator")+ "img"
                + System.getProperty("file.separator") + "swiperPic";
        //如果文件路径不存在 则添加路径

        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File desk = new File(filePath + System.getProperty("file.separator") + fileName);
        //相对文件地址（存储到数据库中）
        String storeSwiperPath = "/img/swiperPic/"+fileName;

        try {
            Swiper swiper = new Swiper();
            swiperFile.transferTo(desk);
            swiper.setName(name);
            swiper.setUrl(storeSwiperPath);
            Boolean flag = swiperService.insert(swiper);

            if (flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"添加成功");
                jsonObject.put("pic",storeSwiperPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"添加失败");
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,e.getMessage());
        }finally {
            return jsonObject;
        }

    }

    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public Object deleteSwiper(@RequestParam("id")Long id){

        return swiperService.delete(id);
    }


}
