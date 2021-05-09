package com.sky.simple.service;

import com.sky.simple.domain.SongList;

import java.util.List;

public interface ScoreService {

    List<SongList> recommendTagBasedUser(Long userId, int size);

    List<SongList> recommendMusicBasedItem(Long userId, int size);

    void setScoreList(Long userId,String tag);

    String getTagType(String tag);
}
