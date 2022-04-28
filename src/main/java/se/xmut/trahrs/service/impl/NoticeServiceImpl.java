package se.xmut.trahrs.service.impl;

import se.xmut.trahrs.domain.model.Notice;
import se.xmut.trahrs.mapper.NoticeMapper;
import se.xmut.trahrs.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公告 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}
