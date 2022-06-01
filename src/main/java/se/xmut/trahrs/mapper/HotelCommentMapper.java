package se.xmut.trahrs.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import se.xmut.trahrs.domain.model.CustomerInteraction;
import se.xmut.trahrs.domain.model.HotelComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import se.xmut.trahrs.domain.vo.HotelCommentVo;

/**
 * <p>
 * 酒店评论 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */

public interface HotelCommentMapper extends BaseMapper<HotelComment> {
    IPage<HotelCommentVo> findHotelcomment(IPage<HotelCommentVo> page,String hotelId);

}
