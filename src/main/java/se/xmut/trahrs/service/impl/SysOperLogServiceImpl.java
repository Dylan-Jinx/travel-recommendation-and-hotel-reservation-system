package se.xmut.trahrs.service.impl;

import se.xmut.trahrs.domain.model.SysOperLog;
import se.xmut.trahrs.mapper.SysOperLogMapper;
import se.xmut.trahrs.service.SysOperLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements SysOperLogService {

}
