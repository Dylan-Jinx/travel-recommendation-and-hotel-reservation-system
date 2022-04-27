package se.xmut.trahrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.service.CustomerService;
import se.xmut.trahrs.entity.Customer;


/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ApiResponse save(@RequestBody Customer customer) {
        return ApiResponse.ok(customerService.saveOrUpdate(customer));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(customerService.removeById(id));
    }

    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(customerService.list());
    }

    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(customerService.list());
    }

    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(customerService.page(new Page<>(pageNum, pageSize)));
    }

}

