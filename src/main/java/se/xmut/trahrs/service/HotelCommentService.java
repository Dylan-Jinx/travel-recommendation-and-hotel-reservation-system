package se.xmut.trahrs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import se.xmut.trahrs.domain.model.HotelComment;
import com.baomidou.mybatisplus.extension.service.IService;
import se.xmut.trahrs.domain.vo.HotelCommentVo;

/**
 * <p>
 * 酒店评论 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
public interface HotelCommentService extends IService<HotelComment> {
    IPage<HotelCommentVo> findHotelcomment(IPage<HotelCommentVo> page,String hotelId);
}
