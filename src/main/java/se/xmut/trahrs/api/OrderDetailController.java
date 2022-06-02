package se.xmut.trahrs.api;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.domain.dto.OrderDetailDto;
import se.xmut.trahrs.domain.model.Scene;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.mapper.OrderDetailMapper;
import se.xmut.trahrs.service.HotelInfoService;
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
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private HotelInfoService hotelInfoService;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @WebLog(description = "添加订单")
    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse save(@RequestBody OrderDetailDto orderDetailDto) throws InvocationTargetException, IllegalAccessException, CloneNotSupportedException {
        List<String> sceneList = orderDetailDto.getOrderIdList().subList(0, orderDetailDto.getLen());
        List<String> hotelList = orderDetailDto.getOrderIdList().subList(orderDetailDto.getLen(), orderDetailDto.getOrderIdList().size());
//        String customerId=orderDetailDto.getCustomerId();
        OrderDetail orderDetail = modelMapper.map(orderDetailDto, OrderDetail.class);
//        QueryWrapper<OrderDetail> orderDetailQueryWrapper=new QueryWrapper<>();
//        orderDetailQueryWrapper.eq("customer_id",customerId).eq("order_id",orderId).eq("order_status",0);
//        OrderDetail orderDetail1=orderDetailService.getOne(orderDetailQueryWrapper);
//        if(orderDetail1!=null){
//            return ApiResponse.error("您有订单还未支付");
//        }
        orderDetail.setOrderNum(IdUtil.objectId());
        orderDetail.setCreateTime(LocalDateTime.now());
        StringBuilder sceneBuilder = new StringBuilder();
        for (int i = 0; i < sceneList.size(); i++) {
            if (i != sceneList.size()-1){
                sceneBuilder.append(sceneList.get(i)).append(";");
            }else {
                sceneBuilder.append(sceneList.get(i));
            }
        }
        Integer cost = orderDetailMapper.sumScenePrice(sceneList);
        orderDetail.setOrderPrice(cost);
        orderDetail.setOrderId(sceneBuilder.toString());
        //默认为0未支付状态
        orderDetail.setOrderStatus(0);
        //1是景区
        orderDetail.setFlag(1);
        orderDetailService.save(orderDetail);

        OrderDetail hotelOrderDetail = orderDetail.clone();

        hotelOrderDetail.setFlag(0);
        hotelOrderDetail.setId(null);
        cost = orderDetailMapper.sumHotelPrice(hotelList);
        hotelOrderDetail.setOrderPrice(cost);
        StringBuilder hotelBuilder = new StringBuilder();
        for (int i = 0; i < hotelList.size(); i++) {
            if (i != hotelList.size()-1){
                hotelBuilder.append(hotelList.get(i)).append(";");
            }else {
                hotelBuilder.append(hotelList.get(i));
            }
        }
        hotelOrderDetail.setOrderId(hotelBuilder.toString());
        return ApiResponse.ok(orderDetailService.save(hotelOrderDetail));
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
                                    @RequestParam String customerId) {
        QueryWrapper<OrderDetail> orderDetailQueryWrapper = new QueryWrapper<>();
        orderDetailQueryWrapper.eq("customer_id", customerId);
        return ApiResponse.ok(orderDetailService.page(new Page<>(pageNum, pageSize), orderDetailQueryWrapper));

    }

    @WebLog(description = "修改订单状态")
    @PutMapping
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse updateStatus(@RequestBody OrderDetail orderDetail) {
        QueryWrapper<OrderDetail> orderDetailQueryWrapper = new QueryWrapper<>();
        orderDetailQueryWrapper.eq("customer_id", orderDetail.getCustomerId())
                .eq("order_num", orderDetail.getOrderNum());
        List<OrderDetail> orderDetails = orderDetailService.list(orderDetailQueryWrapper);
        for (OrderDetail od:orderDetails) {
            od.setOrderStatus(od.getOrderStatus() + 1);
            orderDetailService.updateById(od);
        }
        return ApiResponse.ok(orderDetails);
    }

    @WebLog(description = "周订单销售金额")
    @GetMapping("weekSale")
    public ApiResponse weekTotalSale(){

        return ApiResponse.ok(orderDetailMapper.weekSale(LocalDateTime.now().minusWeeks(1)));
    }

    @WebLog(description = "周订单每天销售金额")
    @GetMapping("weekSaleEachDay")
    public ApiResponse weekSaleEachDay(){

        return  ApiResponse.ok(orderDetailMapper.weekSaleEachDay());
    }

    @WebLog(description = "周酒店每天销售量")
    @GetMapping("weekHotelSaleEachDay")
    public ApiResponse weekHotelSaleEachDay(){

        return ApiResponse.ok(orderDetailMapper.weekHotelSaleEachDay());
    }

    @WebLog(description = "周景点每天销售量")
    @GetMapping("weekSceneSaleEachDay")
    public ApiResponse weekSceneSaleEachDay(){

        return ApiResponse.ok(orderDetailMapper.weekSceneSaleEachDay());
    }

    @WebLog(description = "周酒店总销量")
    @GetMapping("weekHotelSale")
    public ApiResponse weekHotelSale(){

        return ApiResponse.ok(orderDetailMapper.weekHotelSale(LocalDateTime.now().minusWeeks(1)));
    }

    @WebLog(description = "周景点总销量")
    @GetMapping("weekSceneSale")
    public ApiResponse weekSceneSale(){

        return ApiResponse.ok(orderDetailMapper.weekSceneSale(LocalDateTime.now().minusWeeks(1)));
    }
}

