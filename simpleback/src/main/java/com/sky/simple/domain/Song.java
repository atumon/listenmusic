package com.sky.simple.domain;

import java.io.Serializable;

public class Song implements Serializable {
    //主键
    private Long id;
    //歌手
    private String singerName;
    //歌手id
    private Long singerId;
    //歌名
    private String name;
    //歌曲封面
    private String pic;
    //歌曲地址
    private String url;
    //歌词
    private String lyric;
    //专辑
    private String album;
    //时长
    private String duration;


    public Song() {
    }


    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", singerName='" + singerName + '\'' +
                ", singerId=" + singerId +
                ", name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                ", url='" + url + '\'' +
                ", lyric='" + lyric + '\'' +
                ", album='" + album + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public Long getSingerId() {
        return singerId;
    }

    public void setSingerId(Long singerId) {
        this.singerId = singerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
