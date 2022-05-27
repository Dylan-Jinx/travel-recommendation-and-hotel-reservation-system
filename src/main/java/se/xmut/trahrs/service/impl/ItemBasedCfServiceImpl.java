package se.xmut.trahrs.service.impl;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriter;
import cn.hutool.core.util.CharsetUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import se.xmut.trahrs.domain.model.ItemBasedCf;
import se.xmut.trahrs.domain.model.SceneComment;
import se.xmut.trahrs.mapper.ItemBasedCfMapper;
import se.xmut.trahrs.service.ItemBasedCfService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import se.xmut.trahrs.service.SceneCommentService;
import se.xmut.trahrs.util.CFUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-05-25
 */
@Service
public class ItemBasedCfServiceImpl extends ServiceImpl<ItemBasedCfMapper, ItemBasedCf> implements ItemBasedCfService {

    @Autowired
    private CFUtils cfUtils;
    @Autowired
    private BloomFilterRedisServiceImpl bloomFilterRedisService;
    @Autowired
    private SceneCommentService sceneCommentService;

    @Override
    public List<Long> getItemBasedCFRecommendation(Long customerId, Integer num) throws TasteException, IOException {

        if(num==null){
            num = 100;
        }

        GenericItemBasedRecommender recommender = cfUtils.getPearsonRecommender();

        List<RecommendedItem> list = recommender.recommend(customerId, num);

        //当数据不够皮尔森计算出相关性时，尝试使用谷本
        if(list==null || list.size()==0){

            recommender = cfUtils.getTanimotoRecommender();

            list = recommender.recommend(customerId, num);
        }

        System.out.println(list);

        ArrayList<Long> sceneIds = new ArrayList<>();
        for (RecommendedItem item : list) {
            sceneIds.add(item.getItemID());
        }
        return sceneIds;
    }

    @Override
    public List<Long> guessYouLike(Long customerId, Integer num) throws TasteException, IOException {

        if(num==null){
            num = 100;
        }

        GenericItemBasedRecommender recommender = cfUtils.getPearsonRecommender();

        List<RecommendedItem> list = recommender.recommend(customerId, num);

        //当数据不够皮尔森计算出相关性时，尝试使用谷本
        if(list==null || list.size()==0){

            recommender = cfUtils.getTanimotoRecommender();

            list = recommender.recommend(customerId, num);
        }

        List<Long> ans = new ArrayList<>();
        int cnt = 3;

        System.out.println(list);

        for (RecommendedItem item : list) {
            //没推荐过
            if (!bloomFilterRedisService.includeByBloomFilter(String.valueOf(customerId), String.valueOf(item.getItemID()))) {
                cnt--;
                bloomFilterRedisService.addByBloomFilter(String.valueOf(customerId), String.valueOf(item.getItemID()), null);
                ans.add(item.getItemID());
                if (cnt == 0) {
                    break;
                }
            }
        }

        return ans;
    }

    @Override
    public void writeCustomerPreference(Long customerId, Long sceneId, Float rating) throws IOException {
        File csv = ResourceUtils.getFile("classpath:CF.csv");
        BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
        bw.write("\n" + customerId + "," + sceneId + "," + rating);
        bw.close();
    }

    @Override
    public boolean isCanCf(String customerUUID) {
        QueryWrapper<SceneComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_id", customerUUID);
        long cnt = sceneCommentService.count(queryWrapper);
        if(cnt>0){
            return true;
        }
        return false;
    }

}
