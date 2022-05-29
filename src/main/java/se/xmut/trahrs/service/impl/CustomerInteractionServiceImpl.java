package se.xmut.trahrs.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import se.xmut.trahrs.domain.model.Customer;
import se.xmut.trahrs.domain.model.CustomerInteraction;
import se.xmut.trahrs.mapper.CustomerInteractionMapper;
import se.xmut.trahrs.service.CustomerInteractionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 交流记录 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
@Service
public class CustomerInteractionServiceImpl extends ServiceImpl<CustomerInteractionMapper, CustomerInteraction> implements CustomerInteractionService {
   @Autowired
   CustomerInteractionMapper customerInteractionMapper;

    @Override
    public IPage<CustomerInteraction> findCustomerInteraction(IPage<CustomerInteraction> page) {
        IPage<CustomerInteraction> list=customerInteractionMapper.findCustomerInteraction(page);
        return list;
    }

    @Override
    public IPage<CustomerInteraction> findCustomerInteractionCreateTime(IPage<CustomerInteraction> page) {
        IPage<CustomerInteraction> list=customerInteractionMapper.findCustomerInteractionCreateTime(page);
        return list;
    }
}



