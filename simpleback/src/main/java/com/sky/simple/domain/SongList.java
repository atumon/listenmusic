package com.sky.simple.domain;

import java.io.Serializable;
import java.sql.Date;

public class SongList implements Serializable {

    //主键
    private Long id;
    //歌单名
    private String name;
    //创建者id
    private Long creatorId;
    //创建者名
    private String creatorName;
    //歌单封面
    private String pic;
    //共享
    private int share;
    //类型
    private String tag1;
    //风格
    private String tag2;
    //语言
    private String tag3;
    //介绍
    private String description;
    //收藏次数
    private Long collectTimes;
    //热度
    private Long heat;
    //歌单里的歌曲数量
    private int songNumber;
    //是否自动更新歌单封面
    private Boolean autoUpPic;
    //创建时间
    private Date createTime;

    @Override
    public String toString() {
        return "SongList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creatorId=" + creatorId +
                ", creatorName='" + creatorName + '\'' +
                ", pic='" + pic + '\'' +
                ", share=" + share +
                ", tag1='" + tag1 + '\'' +
                ", tag2='" + tag2 + '\'' +
                ", tag3='" + tag3 + '\'' +
                ", description='" + description + '\'' +
                ", collectTimes=" + collectTimes +
                ", heat=" + heat +
                ", songNumber=" + songNumber +
                ", autoUpPic=" + autoUpPic +
                ", createTime=" + createTime +
                '}';
    }
    public SongList() {}

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getAutoUpPic() {
        return autoUpPic;
    }

    public void setAutoUpPic(Boolean autoUpPic) {
        this.autoUpPic = autoUpPic;
    }

    public int getSongNumber() {
        return songNumber;
    }

    public void setSongNumber(int songNumber) {
        this.songNumber = songNumber;
    }

    public Long getHeat() {
        return heat;
    }

    public void setHeat(Long heat) {
        this.heat = heat;
    }

    public Long getCollectTimes() {
        return collectTimes;
    }

    public void setCollectTimes(Long collectTimes) {
        this.collectTimes = collectTimes;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
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

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
