package se.xmut.trahrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.service.CustomerInteractionService;
import se.xmut.trahrs.entity.CustomerInteraction;


/**
 * <p>
 * 交流记录 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/customerInteraction")
public class CustomerInteractionController {

    final Logger logger = LoggerFactory.getLogger(CustomerInteractionController.class);
    @Autowired
    private CustomerInteractionService customerInteractionService;

    @PostMapping
    public ApiResponse save(@RequestBody CustomerInteraction customerInteraction) {
        return ApiResponse.ok(customerInteractionService.saveOrUpdate(customerInteraction));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(customerInteractionService.removeById(id));
    }

    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(customerInteractionService.list());
    }

    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(customerInteractionService.list());
    }

    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(customerInteractionService.page(new Page<>(pageNum, pageSize)));
    }

}

