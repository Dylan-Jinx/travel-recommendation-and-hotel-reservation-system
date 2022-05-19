package se.xmut.trahrs.service.impl;

import se.xmut.trahrs.domain.model.Customer;
import se.xmut.trahrs.mapper.CustomerMapper;
import se.xmut.trahrs.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-05-19
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
