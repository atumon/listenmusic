package com.sky.simple.domain;

import javax.xml.crypto.Data;
import java.io.Serializable;

public class User implements Serializable {

    //主键
    private Long id;
    //用户名
    private String username;
    //密码
    private String password;
    //性别
    private Integer sex;
    //生日
    private String birth;
    //电话
    private String phoneNum;
    //邮箱
    private String email;
    //地址
    private String location;
    //描述
    private String description;
    //头像
    private String avatar;
    //创建时间
    private String createTime;
    //更新时间
    private String updateTime;

    public User(Long id, String username, String password, Integer sex, String birth, String phoneNum, String email, String location, String description, String avatar, String createTime, String updateTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.birth = birth;
        this.phoneNum = phoneNum;
        this.email = email;
        this.location = location;
        this.description = description;
        this.avatar = avatar;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", birth='" + birth + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }


    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
