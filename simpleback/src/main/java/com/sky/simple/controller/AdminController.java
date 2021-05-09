package com.sky.simple.controller;

import com.alibaba.fastjson.JSONObject;
import com.sky.simple.domain.UserToken;
import com.sky.simple.service.AdminService;
import com.sky.simple.utils.Consts;
import com.sky.simple.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 判断登录
     */
    @RequestMapping(value = "admin/login/status",method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest request) throws Exception {

        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        System.out.println(name +"   "+ password);

        Long AdminId = adminService.verifyPassword(name,password);

        if (AdminId != null){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"登录成功");
            UserToken userToken = new UserToken(AdminId,name);

            String token = TokenUtils.generateToken(userToken,0);

            jsonObject.put(Consts.TOKEN,token);

            System.out.println(jsonObject.toString());

            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名或密码错误");
            System.out.println(jsonObject.toString());
            return jsonObject;
        }

    }
}
