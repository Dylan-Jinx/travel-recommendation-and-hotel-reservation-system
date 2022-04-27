package se.xmut.trahrs.service.impl;

import se.xmut.trahrs.entity.SysJob;
import se.xmut.trahrs.mapper.SysJobMapper;
import se.xmut.trahrs.service.SysJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务调度表 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@Service
public class SysJobServiceImpl extends ServiceImpl<SysJobMapper, SysJob> implements SysJobService {

}
