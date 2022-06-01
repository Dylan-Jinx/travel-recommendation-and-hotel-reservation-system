package se.xmut.trahrs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import se.xmut.trahrs.domain.model.InteractionComment;
import se.xmut.trahrs.domain.vo.InteractionCommentVo;
import se.xmut.trahrs.mapper.InteractionCommentMapper;
import se.xmut.trahrs.service.InteractionCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 交流评论 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
@Service
public class InteractionCommentServiceImpl extends ServiceImpl<InteractionCommentMapper, InteractionComment> implements InteractionCommentService {
@Autowired
InteractionCommentMapper interactionCommentMapper;
    @Override
    public List<InteractionCommentVo> findIntercomment(String interactionId) {
        return interactionCommentMapper.findIntercomment(interactionId);
    }
}
