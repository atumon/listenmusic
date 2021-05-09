package org.sky.listenmusic.Bean;

import java.util.Date;

public class Album {

    private int AlbumId;
    private String AlbumName;
    private String AlbumSinger;
    private Date Time;

    public int getAlbumId() {
        return AlbumId;
    }

    public void setAlbumId(int albumId) {
        AlbumId = albumId;
    }

    public String getAlbumName() {
        return AlbumName;
    }

    public void setAlbumName(String albumName) {
        AlbumName = albumName;
    }

    public String getAlbumSinger() {
        return AlbumSinger;
    }

    public void setAlbumSinger(String albumSinger) {
        AlbumSinger = albumSinger;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date time) {
        Time = time;
    }

    public Album(int albumId, String albumName, String albumSinger, Date time) {
        AlbumId = albumId;
        AlbumName = albumName;
        AlbumSinger = albumSinger;
        Time = time;
    }
}
