package se.xmut.trahrs.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import se.xmut.trahrs.domain.model.Customer;
import se.xmut.trahrs.domain.model.CustomerInteraction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 交流记录 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
public interface CustomerInteractionMapper extends BaseMapper<CustomerInteraction> {
    IPage<CustomerInteraction> findCustomerInteraction(IPage<CustomerInteraction> page);
    IPage<CustomerInteraction> findCustomerInteractionCreateTime(IPage<CustomerInteraction> page);
}
