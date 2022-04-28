package se.xmut.trahrs.service.impl;

import se.xmut.trahrs.domain.model.RestaurantComment;
import se.xmut.trahrs.mapper.RestaurantCommentMapper;
import se.xmut.trahrs.service.RestaurantCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 餐馆评论 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
@Service
public class RestaurantCommentServiceImpl extends ServiceImpl<RestaurantCommentMapper, RestaurantComment> implements RestaurantCommentService {

}
