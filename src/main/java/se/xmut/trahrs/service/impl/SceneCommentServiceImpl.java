package se.xmut.trahrs.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import se.xmut.trahrs.domain.model.SceneComment;
import se.xmut.trahrs.domain.vo.SceneCommentVo;
import se.xmut.trahrs.mapper.SceneCommentMapper;
import se.xmut.trahrs.mapper.SceneMapper;
import se.xmut.trahrs.service.SceneCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 景区评论 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
@Service
public class SceneCommentServiceImpl extends ServiceImpl<SceneCommentMapper, SceneComment> implements SceneCommentService {
@Autowired
    private SceneCommentMapper sceneCommentMapper;
    @Override
    public IPage<SceneCommentVo> findScenecomment(IPage<SceneCommentVo> page,String sceneId) {
        IPage<SceneCommentVo> sceneCommentVoIPage=sceneCommentMapper.findScenecomment(page,sceneId);
        return sceneCommentVoIPage;
    }
}
