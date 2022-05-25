package se.xmut.trahrs.service;

import se.xmut.trahrs.domain.model.ItemBasedCf;
import com.baomidou.mybatisplus.extension.service.IService;
import se.xmut.trahrs.domain.model.Scene;

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
     * 基于物品的协同过滤
     * @param customerId 用户id
     * @return 推荐的景点列表
     */
    public List<Scene> getItemBasedCFRecommendation(String customerId);
}
