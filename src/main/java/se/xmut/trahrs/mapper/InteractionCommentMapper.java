package se.xmut.trahrs.mapper;

import se.xmut.trahrs.domain.model.InteractionComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import se.xmut.trahrs.domain.vo.InteractionCommentVo;

import java.util.List;

/**
 * <p>
 * 交流评论 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
public interface InteractionCommentMapper extends BaseMapper<InteractionComment> {
  List<InteractionCommentVo> findIntercomment(String interactionId);
}
