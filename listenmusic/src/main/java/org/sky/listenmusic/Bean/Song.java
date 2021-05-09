package org.sky.listenmusic.Bean;

public class Song {


    private int SongId;
    private String SongName;
    private String SongSinger;
    private String Type;
    private String Style;
    private String Language;
    private String PlayTimes;

    public int getSongId() {
        return SongId;
    }

    public void setSongId(int songId) {
        SongId = songId;
    }

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String songName) {
        SongName = songName;
    }

    public String getSongSinger() {
        return SongSinger;
    }

    public void setSongSinger(String songSinger) {
        SongSinger = songSinger;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getStyle() {
        return Style;
    }

    public void setStyle(String style) {
        Style = style;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getPlayTimes() {
        return PlayTimes;
    }

    public void setPlayTimes(String playTimes) {
        PlayTimes = playTimes;
    }

    public Song(int songId, String songName, String songSinger, String type, String style, String language) {
        SongId = songId;
        SongName = songName;
        SongSinger = songSinger;
        Type = type;
        Style = style;
        Language = language;
    }
}
