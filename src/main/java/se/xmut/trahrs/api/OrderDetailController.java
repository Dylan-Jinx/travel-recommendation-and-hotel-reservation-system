package se.xmut.trahrs.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.OrderDetailService;
import se.xmut.trahrs.domain.model.OrderDetail;


/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {

    final Logger logger = LoggerFactory.getLogger(OrderDetailController.class);
    @Autowired
    private OrderDetailService orderDetailService;

    @WebLog(description = "添加订单")
    @PostMapping
    public ApiResponse save(@RequestBody OrderDetail orderDetail) {
        return ApiResponse.ok(orderDetailService.saveOrUpdate(orderDetail));
    }

    @WebLog(description = "用id删除订单")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(orderDetailService.removeById(id));
    }

    @WebLog(description = "查询全部订单")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(orderDetailService.list());
    }

    @WebLog(description = "用id查找订单")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(orderDetailService.getById(id));
    }

    @WebLog(description = "分页订单")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(orderDetailService.page(new Page<>(pageNum, pageSize)));
    }

}

