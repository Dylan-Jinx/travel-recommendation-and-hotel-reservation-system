package se.xmut.trahrs.service.impl;

import se.xmut.trahrs.entity.CustomerInteraction;
import se.xmut.trahrs.mapper.CustomerInteractionMapper;
import se.xmut.trahrs.service.CustomerInteractionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 交流记录 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@Service
public class CustomerInteractionServiceImpl extends ServiceImpl<CustomerInteractionMapper, CustomerInteraction> implements CustomerInteractionService {

}
