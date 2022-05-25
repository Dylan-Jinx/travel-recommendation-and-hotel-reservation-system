package se.xmut.trahrs.service.impl;

import se.xmut.trahrs.domain.model.ItemBasedCf;
import se.xmut.trahrs.domain.model.Scene;
import se.xmut.trahrs.mapper.ItemBasedCfMapper;
import se.xmut.trahrs.service.ItemBasedCfService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class ItemBasedCfServiceImpl extends ServiceImpl<ItemBasedCfMapper, ItemBasedCf> implements ItemBasedCfService {

    @Override
    public List<Scene> getItemBasedCFRecommendation(String customerId) {
        return null;
    }
}
