package se.xmut.trahrs.service.impl;

import se.xmut.trahrs.entity.Restaurant;
import se.xmut.trahrs.mapper.RestaurantMapper;
import se.xmut.trahrs.service.RestaurantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 餐馆信息 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@Service
public class RestaurantServiceImpl extends ServiceImpl<RestaurantMapper, Restaurant> implements RestaurantService {

}
