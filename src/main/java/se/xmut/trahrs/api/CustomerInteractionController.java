package se.xmut.trahrs.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.CustomerInteractionService;
import se.xmut.trahrs.domain.model.CustomerInteraction;


/**
 * <p>
 * 交流记录 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/customerInteraction")
public class CustomerInteractionController {

    final Logger logger = LoggerFactory.getLogger(CustomerInteractionController.class);
    @Autowired
    private CustomerInteractionService customerInteractionService;

    @WebLog(description = "添加交流记录")
    @PostMapping
    public ApiResponse save(@RequestBody CustomerInteraction customerInteraction) {
        return ApiResponse.ok(customerInteractionService.saveOrUpdate(customerInteraction));
    }

    @WebLog(description = "用id删除交流记录")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(customerInteractionService.removeById(id));
    }

    @WebLog(description = "查询全部交流记录")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(customerInteractionService.list());
    }

    @WebLog(description = "用id查找交流记录")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(customerInteractionService.getById(id));
    }

    @WebLog(description = "分页交流记录")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(customerInteractionService.page(new Page<>(pageNum, pageSize)));
    }

}

