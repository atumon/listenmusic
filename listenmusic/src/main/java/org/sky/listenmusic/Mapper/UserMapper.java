package org.sky.listenmusic.Mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.sky.listenmusic.Bean.User;

@Mapper
public interface UserMapper {

    @Select("Select * from User where UserId = #{UserId}")
    public User SelectIdFromUser(Long UserId);
}
