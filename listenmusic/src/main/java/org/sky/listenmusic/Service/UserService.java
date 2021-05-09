package org.sky.listenmusic.Service;


import org.sky.listenmusic.Bean.User;
import org.sky.listenmusic.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User SelectIdFromUser(Long UserId){
        return userMapper.SelectIdFromUser(UserId);
    }

}
