package se.xmut.trahrs.service;

import org.apache.mahout.cf.taste.common.TasteException;
import se.xmut.trahrs.domain.model.Customer;
import se.xmut.trahrs.domain.model.ItemBasedCf;
import com.baomidou.mybatisplus.extension.service.IService;
import se.xmut.trahrs.domain.model.Scene;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-05-25
 */
public interface ItemBasedCfService extends IService<ItemBasedCf> {

    /**
     * 基于物品的协同过滤，非冷启动情况下可使用
     * @param customerId 用户id 不是uuid
     * @param num 推荐个数，填null默认查出100条
     * @return 推荐的景点id列表 不是uuid 返回controller后再选择使用分页或融合其他列表
     * @throws TasteException
     */
    public List<Long> getItemBasedCFRecommendation(Long customerId, Integer num) throws TasteException, IOException;

    /**
     * 猜你喜欢，推荐3个用户可能会喜欢的相似景点，点击换一换切换下一个，切换3次回到第一个
     * @param customerId 用户id 不是uuid
     * @param num 推荐个数 填null默认查出100条 num要大于guessNum
     * @param guessNum 猜你喜欢换一换支持条数 填null默认3条
     * @return 布隆过滤后推荐的3个景点id，不是uuid
     * @throws TasteException
     */
    public List<Long> guessYouLike(Long customerId, Integer num, Integer guessNum) throws TasteException, IOException;

    /**
     * 写入用户评分情况
     * @param customerId 用户id 不是uuid
     * @param sceneId 景区id 不是uuid
     * @param rating 评分情况
     */
    public void writeCustomerPreference(Long customerId, Long sceneId, Float rating) throws IOException;

    /**
     * 用户id
     * @param customerUUID 用户uuid
     */
    public boolean isCanCf(String customerUUID);

}
