package com.sky.simple.recommend;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class MusicRecommend {
    final private int NEIGHBORHOOD_NUM = 3;
    @Resource(name = "MySQLJDBCTagDataModel")
    private DataModel tagDataModel;

    @Resource(name = "MySQLJDBCListDataModel")
    private DataModel listDataModel;


    //将RecommendList转化为LongList
    private List<Long> getRecommendedItemIDs(List<RecommendedItem> recommendations) {
        List<Long> recommendItems = new ArrayList<>();
        for (int i = 0; i < recommendations.size(); i++) {
            RecommendedItem recommendedItem = recommendations.get(i);
            recommendItems.add(recommendedItem.getItemID());
        }
        return recommendItems;
    }

    public List<Long> userBasedTagRecommend (long userId,int size) throws TasteException{
        UserSimilarity similarity = new EuclideanDistanceSimilarity(tagDataModel);

        UserSimilarity similarity2 = new EuclideanDistanceSimilarity(listDataModel);

        NearestNUserNeighborhood neighbor = new NearestNUserNeighborhood(NEIGHBORHOOD_NUM,similarity,tagDataModel);

        Recommender recommender = new CachingRecommender(new GenericUserBasedRecommender(listDataModel, neighbor, similarity));

        List<RecommendedItem> recommendations = recommender.recommend(userId, size);


        return getRecommendedItemIDs(recommendations);
    }

    public List<Long> itemBasedRecommender(long userID, int size) throws TasteException {

        ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(tagDataModel);

        Recommender recommender = new GenericItemBasedRecommender(tagDataModel, itemSimilarity);

        List<RecommendedItem> recommendations = recommender.recommend(userID, size);

        return getRecommendedItemIDs(recommendations);
    }


}
