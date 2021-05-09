package com.sky.simple.domain;

import java.io.Serializable;

public class Singer implements Serializable {
    //主键
    private Long id;
    //姓名
    private String name;
    //性别
    private Integer sex;
    //头像
    private String pic;
    //生日
    private String birth;
    //地区
    private String location;
    //描述
    private String description;
    //热度
    private Long heat;

    public Singer(Long id, String name, Integer sex,  String birth, String location, String description) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.location = location;
        this.description = description;
    }

    public Singer(String name, Integer sex,  String birth, String location, String description) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.location = location;
        this.description = description;
    }

    public Singer() {
    }

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", pic='" + pic + '\'' +
                ", birth='" + birth + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", heat=" + heat +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
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

    public Long getHeat() {
        return heat;
    }

    public void setHeat(Long heat) {
        this.heat = heat;
    }
}