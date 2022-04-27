package se.xmut.trahrs.service.impl;

import se.xmut.trahrs.entity.OrderDetail;
import se.xmut.trahrs.mapper.OrderDetailMapper;
import se.xmut.trahrs.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}
