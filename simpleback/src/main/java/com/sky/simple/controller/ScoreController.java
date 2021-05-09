package com.sky.simple.controller;

import com.sky.simple.dao.ListMapper;
import com.sky.simple.dao.ScoreMapper;
import com.sky.simple.domain.Score;
import com.sky.simple.domain.SongList;
import com.sky.simple.service.ScoreService;
import com.sky.simple.service.SingerService;
import com.sky.simple.service.SongListService;
import com.sky.simple.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("score")
public class ScoreController {
    final private int RECOMMENDER_NUM = 5;    //推荐结果个数
    @Autowired
    private ScoreService scoreService;

    @Autowired
    private SongListService songListService;

    /**
     * 记录评分
     * @return
     */
    @RequestMapping(value = "/recordScore",method = RequestMethod.POST)
    public Object recordScore(HttpServletRequest request){
        Long userId = Long.parseLong(request.getParameter("id").trim());
        int num = Integer.parseInt(request.getParameter("num").trim());

        switch (num){
            case 0:
                break;
            case 3:
                String tag3 = request.getParameter("tag3").trim();
                scoreService.setScoreList(userId,tag3);
            case 2:
                String tag2 = request.getParameter("tag2").trim();
                scoreService.setScoreList(userId,tag2);
            case 1:
                String tag1 = request.getParameter("tag1").trim();
                scoreService.setScoreList(userId,tag1);
                break;
        }

        return null;
    }
    /**
     * 记录评分
     * @return
     */
    @RequestMapping(value = "/collectScore",method = RequestMethod.GET)
    public Object collectScore(@RequestParam("listId")Long listId,@RequestParam("userId")Long userId){
        SongList songList = songListService.selectByPrimaryKey(listId);

        if (songList.getTag3() != null)
            scoreService.setScoreList(userId,songList.getTag3());
        if (songList.getTag2() != null)
            scoreService.setScoreList(userId,songList.getTag2());
        if (songList.getTag1() != null)
            scoreService.setScoreList(userId,songList.getTag1());

        return null;
    }

    /**
     * 推荐
     */
    @RequestMapping(value = "/recommend",method = RequestMethod.GET)
    public Object recommendList(@RequestParam("userId")Long userId){
        return scoreService.recommendMusicBasedItem(userId,RECOMMENDER_NUM);
    }
}
