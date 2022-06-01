package se.xmut.trahrs.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import se.xmut.trahrs.domain.model.HotelComment;
import se.xmut.trahrs.domain.vo.HotelCommentVo;
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
@Autowired
HotelCommentMapper hotelCommentMapper;
    @Override
    public IPage<HotelCommentVo> findHotelcomment(IPage<HotelCommentVo> page,String hotelId) {
        IPage<HotelCommentVo> list=hotelCommentMapper.findHotelcomment(page,hotelId);
        return list;
    }
}
