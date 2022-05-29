package se.xmut.trahrs.api;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.domain.dto.OrderDetailDto;
import se.xmut.trahrs.domain.model.Scene;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.OrderDetailService;
import se.xmut.trahrs.domain.model.OrderDetail;
import se.xmut.trahrs.service.SceneService;


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
    @Autowired
    private ModelMapper modelMapper;
    @WebLog(description = "添加订单")
    @PostMapping
    public ApiResponse save(@RequestBody OrderDetailDto orderDetailDto) {
        String orderId=orderDetailDto.getOrderId();
        String customerId=orderDetailDto.getCustomerId();
        QueryWrapper<OrderDetail> orderDetailQueryWrapper=new QueryWrapper<>();
        orderDetailQueryWrapper.eq("customer_id",customerId).eq("order_id",orderId).eq("order_status",0);
        OrderDetail orderDetail1=orderDetailService.getOne(orderDetailQueryWrapper);
        if(orderDetail1!=null){
            return ApiResponse.error("您有订单还未支付");
        }
        OrderDetail orderDetail=modelMapper.map(orderDetailDto,OrderDetail.class);
        orderDetail.setOrderNum(IdUtil.objectId());
        orderDetail.setCreateTime(LocalDateTime.now());
        //默认为0未支付状态
        orderDetail.setOrderStatus(0);
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

    @WebLog(description = "查询顾客自己的订单")
    @GetMapping("/findCustomer")
    public ApiResponse findCustomer(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize,
                                    @RequestParam String customerId){
        QueryWrapper<OrderDetail> orderDetailQueryWrapper=new QueryWrapper<>();
        orderDetailQueryWrapper.eq("customer_id",customerId);
        return ApiResponse.ok(orderDetailService.page(new Page<>(pageNum,pageSize),orderDetailQueryWrapper));

    }
    @WebLog(description = "修改订单状态")
    @PutMapping
    public ApiResponse updateStatus(@RequestBody OrderDetail orderDetail){
        QueryWrapper<OrderDetail> orderDetailQueryWrapper=new QueryWrapper<>();
        orderDetailQueryWrapper.eq("customer_id",orderDetail.getCustomerId())
                .eq("order_num",orderDetail.getOrderNum());
        orderDetail.setOrderStatus(orderDetail.getOrderStatus()+1);
        return ApiResponse.ok(orderDetailService.update(orderDetail,orderDetailQueryWrapper));
    }

}

