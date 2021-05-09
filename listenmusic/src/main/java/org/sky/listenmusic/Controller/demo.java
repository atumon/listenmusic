package org.sky.listenmusic.Controller;

import org.apache.ibatis.annotations.Param;
import org.sky.listenmusic.Bean.User;
import org.sky.listenmusic.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demo {

    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "User",method = RequestMethod.GET)
    public User demo(Long UserId){

        System.out.println(userMapper.SelectIdFromUser(UserId));

        return userMapper.SelectIdFromUser(UserId);

    }



}
