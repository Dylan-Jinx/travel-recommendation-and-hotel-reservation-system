package se.xmut.trahrs.service.impl;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import se.xmut.trahrs.domain.model.ItemBasedCf;
import se.xmut.trahrs.mapper.ItemBasedCfMapper;
import se.xmut.trahrs.service.ItemBasedCfService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import se.xmut.trahrs.util.CFUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-05-25
 */
@Service
/**FIXME 优化性能*/
public class ItemBasedCfServiceImpl extends ServiceImpl<ItemBasedCfMapper, ItemBasedCf> implements ItemBasedCfService {

    @Autowired
    private CFUtils cfUtils;
    @Autowired
    private BloomFilterRedisServiceImpl bloomFilterRedisService;

    @Override
    public List<Long> getItemBasedCFRecommendation(Long customerId, Integer num) throws TasteException {

        GenericItemBasedRecommender recommender = cfUtils.getItemBasedRecommender();

        List<RecommendedItem> list = recommender.recommend(customerId, num);
        if(list.size()==0){
            return null;
        }

        System.out.println(list);

        ArrayList<Long> sceneIds = new ArrayList<>();
        for (RecommendedItem item:list) {
            sceneIds.add(item.getItemID());
        }
        return sceneIds;
    }

    @Override
    public List<Long> guessYouLike(Long customerId, Integer num) throws TasteException {

        GenericItemBasedRecommender recommender = cfUtils.getItemBasedRecommender();

        List<RecommendedItem> list;
        if(num==null){
            list = recommender.recommend(customerId, 100);
        }else {
            list = recommender.recommend(customerId, num);
        }

        List<Long> ans = new ArrayList<>();
        int cnt = 3;

        System.out.println(list);

        for (RecommendedItem item:list) {
            //没推荐过
            if(!bloomFilterRedisService.includeByBloomFilter(String.valueOf(customerId), String.valueOf(item.getItemID()))){
                cnt--;
                bloomFilterRedisService.addByBloomFilter(String.valueOf(customerId), String.valueOf(item.getItemID()), null);
                ans.add(item.getItemID());
                if(cnt==0){
                    break;
                }
            }
        }

        return ans;
    }
}
