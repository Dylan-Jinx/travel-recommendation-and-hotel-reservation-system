package se.xmut.trahrs.api;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.net.Ipv4Util;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.domain.dto.CustomerDto;
import se.xmut.trahrs.domain.dto.LoginDto;
import se.xmut.trahrs.domain.vo.CustomerVo;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.CustomerService;
import se.xmut.trahrs.domain.model.Customer;
import se.xmut.trahrs.util.IPUtils;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ModelMapper modelMapper;

    @WebLog(description = "用户注册")
    @PostMapping
    public ApiResponse save(@RequestBody CustomerDto customerDto) {

        Customer customer = modelMapper.map(customerDto, Customer.class);

        String phone = customer.getPhone();
        QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
        customerQueryWrapper.eq("phone",phone);

        if(!ObjectUtil.isNull(customerService.getOne(customerQueryWrapper))){
            return ApiResponse.error("该用户已被注册");
        }

        String originPwd = customerDto.getCustomerPwd();
        String secretPwd = MD5.create().digestHex(originPwd);

        customer.setCustomerPwd(secretPwd);
        customer.setCustomerId(IdUtil.objectId());
        customer.setCreateTime(LocalDateTimeUtil.now());
        customer.setRemoveFlag(0);
        customerService.save(customer);
        return ApiResponse.ok("注册成功");
    }

    @WebLog(description = "用户登录")
    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginDto loginDto, HttpServletRequest request){

        String userName = loginDto.getUserName();
        String originPwd = loginDto.getUserPwd();
        String secretPwd = MD5.create().digestHex(originPwd);
        String requestIPAddress = IPUtils.getIpAddr(request);
        logger.info("IP地址发起请求："+requestIPAddress);

        QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
        customerQueryWrapper.eq("phone", userName)
                .eq("customer_pwd", secretPwd);
        Customer customer = customerService.getOne(customerQueryWrapper);

        customer.setLastLoginTime(LocalDateTimeUtil.now());
        customer.setLastLoginIp(requestIPAddress);
        customerService.saveOrUpdate(customer);

        CustomerVo customerVo = modelMapper.map(customer,CustomerVo.class);
        return ApiResponse.ok("获取成功", customerVo);
    }

    @WebLog(description = "用户信息修改")
    @PutMapping
    public ApiResponse update(Customer customer){
        return ApiResponse.ok("操作成功", customer);
    }

    @WebLog(description = "用id删除用户")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(customerService.removeById(id));
    }

    @WebLog(description = "查询全部用户")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(customerService.list());
    }

    @WebLog(description = "用id查找用户")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(customerService.getById(id));
    }

    @WebLog(description = "分页用户")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(customerService.page(new Page<>(pageNum, pageSize)));
    }
}

