package se.xmut.trahrs.service;

import se.xmut.trahrs.domain.model.InteractionComment;
import com.baomidou.mybatisplus.extension.service.IService;
import se.xmut.trahrs.domain.vo.InteractionCommentVo;

import java.util.List;

/**
 * <p>
 * 交流评论 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
public interface InteractionCommentService extends IService<InteractionComment> {
    List<InteractionCommentVo> findIntercomment(String interactionId);
}
