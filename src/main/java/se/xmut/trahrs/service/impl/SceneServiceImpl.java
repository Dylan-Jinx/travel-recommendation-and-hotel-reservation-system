package se.xmut.trahrs.service.impl;

import se.xmut.trahrs.entity.Scene;
import se.xmut.trahrs.mapper.SceneMapper;
import se.xmut.trahrs.service.SceneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 景点信息 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@Service
public class SceneServiceImpl extends ServiceImpl<SceneMapper, Scene> implements SceneService {

}
