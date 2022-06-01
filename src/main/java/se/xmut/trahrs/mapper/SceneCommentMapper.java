package se.xmut.trahrs.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import se.xmut.trahrs.domain.model.SceneComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import se.xmut.trahrs.domain.vo.HotelCommentVo;
import se.xmut.trahrs.domain.vo.SceneCommentVo;

/**
 * <p>
 * 景区评论 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
public interface SceneCommentMapper extends BaseMapper<SceneComment> {
    IPage<SceneCommentVo> findScenecomment(IPage<SceneCommentVo> page,String sceneId);

}
