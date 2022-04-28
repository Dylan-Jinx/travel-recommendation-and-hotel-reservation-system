package se.xmut.trahrs.service.impl;

import se.xmut.trahrs.domain.model.HotelComment;
import se.xmut.trahrs.mapper.HotelCommentMapper;
import se.xmut.trahrs.service.HotelCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 酒店评论 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
@Service
public class HotelCommentServiceImpl extends ServiceImpl<HotelCommentMapper, HotelComment> implements HotelCommentService {

}
