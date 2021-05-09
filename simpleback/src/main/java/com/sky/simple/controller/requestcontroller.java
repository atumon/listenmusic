package com.sky.simple.controller;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class requestcontroller {

    @RequestMapping("regist")
    public String regist(String username,String password){

        System.out.println(username);
        System.out.println(password);

        return "regist success";
    }

    @ResponseBody
    @RequestMapping(value = "UserLogin",method = RequestMethod.POST)
    public String login(@RequestBody JSONObject params){

        System.out.println(params.toString());

        String username = params.getString("name");
        String password = params.getString("password");

        if ("xiaoming".equals(username)&&"123".equals(password)){

            System.out.println(username);
            System.out.println(password);
            return "success";

        }else {
            System.out.println("这 是用户名：" + username );
            System.out.println("这是密码：" + password);
            return "failed";
        }
    }


}
