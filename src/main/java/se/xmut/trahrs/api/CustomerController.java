package se.xmut.trahrs.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.CustomerService;
import se.xmut.trahrs.domain.model.Customer;


/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;

    @WebLog(description = "添加用户")
    @PostMapping
    public ApiResponse save(@RequestBody Customer customer) {
        return ApiResponse.ok(customerService.saveOrUpdate(customer));
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
        return ApiResponse.ok(customerService.list());
    }

    @WebLog(description = "分页用户")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(customerService.page(new Page<>(pageNum, pageSize)));
    }

}

