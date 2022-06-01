package se.xmut.trahrs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import se.xmut.trahrs.domain.model.SceneComment;
import com.baomidou.mybatisplus.extension.service.IService;
import se.xmut.trahrs.domain.vo.HotelCommentVo;
import se.xmut.trahrs.domain.vo.SceneCommentVo;

/**
 * <p>
 * 景区评论 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
public interface SceneCommentService extends IService<SceneComment> {
    IPage<SceneCommentVo> findScenecomment(IPage<SceneCommentVo> page);
}
