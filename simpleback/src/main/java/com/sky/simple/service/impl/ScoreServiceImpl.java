package com.sky.simple.service.impl;

import com.sky.simple.dao.ListMapper;
import com.sky.simple.dao.ScoreMapper;
import com.sky.simple.domain.Score;
import com.sky.simple.domain.SongList;
import com.sky.simple.recommend.MusicRecommend;
import com.sky.simple.service.ScoreService;
import com.sky.simple.utils.Consts;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private MusicRecommend musicRecommend;

    @Autowired
    private ListMapper listMapper;

    @Autowired
    private ScoreMapper scoreMapper;

    /**
     * 基于用户的推荐
     * @param userId
     * @param size
     * @return
     */
    @Override
    public List<SongList> recommendTagBasedUser(Long userId, int size) {
        List<Long> recommendItems =null;
        try {
            recommendItems = musicRecommend.userBasedTagRecommend(userId,size);
        } catch (TasteException e){
            e.printStackTrace();
        }
        System.out.println(recommendItems.toString());

        return listMapper.queryMusicByIds(recommendItems);
    }

    /**
     * 基于物品的推荐
     * @param userId
     * @param size
     * @return
     */
    @Override
    public List<SongList> recommendMusicBasedItem(Long userId, int size) {
        List<Long> recommendItems =null;

        try {
            recommendItems = musicRecommend.itemBasedRecommender(userId,size);
        } catch (TasteException e){
            e.printStackTrace();
        }
        System.out.println(recommendItems.toString());

        return listMapper.queryMusicByIds(recommendItems);
    }

    /**
     * 返回评分list
     */
    @Override
    public void setScoreList(Long userId,String tag){
        List<Score> scoreList = scoreMapper.selectAllScore(userId);
        DecimalFormat df = new DecimalFormat("0.00");
        Boolean flag = false;
        if (scoreList != null) {
            for (Score score:scoreList){
                if (getTagType(score.getTag()).equals(getTagType(tag))) {
                    float grade = score.getScore();
                    if (grade >= 2) {
                        grade = Float.parseFloat(df.format(grade - grade * 0.05));
                    }
                    score.setScore(grade);
                }
                if (score.getTag().equals(tag)) {
                    score.setScore(score.getScore() + 1);
                    flag = true;
                }
            }
            if (!flag){
                Score score = new Score(userId,tag,1);
                scoreMapper.insertScore(score);
            }

            scoreList.forEach(score -> {
                scoreMapper.updateScore(score);
            });

        }else {
            Score score = new Score(userId,tag,1);
            scoreMapper.insertScore(score);
        }

    }

    /**
     * 方法：返回当前标签类型
     */
    @Override
    public String getTagType(String tag){
        Consts consts = new Consts();
        if (Consts.language.indexOf(tag) != -1){
            return "language";
        }else if (Consts.type.indexOf(tag) != -1){
            return "type";
        }else {
            return "emotion";
        }
    }
}
