package se.xmut.trahrs.service.impl;

import se.xmut.trahrs.entity.SceneDetail;
import se.xmut.trahrs.mapper.SceneDetailMapper;
import se.xmut.trahrs.service.SceneDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 景点信息详细（主要是图片） 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@Service
public class SceneDetailServiceImpl extends ServiceImpl<SceneDetailMapper, SceneDetail> implements SceneDetailService {

}
