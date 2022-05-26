package se.xmut.trahrs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import se.xmut.trahrs.domain.model.Customer;
import se.xmut.trahrs.domain.model.CustomerInteraction;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 交流记录 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
public interface CustomerInteractionService extends IService<CustomerInteraction> {
    IPage<Customer> findCustomerInteraction(IPage<Customer> page);
    IPage<Customer> findCustomerInteractionCreateTime(IPage<Customer> page);
}
