package se.xmut.trahrs.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import se.xmut.trahrs.domain.model.OrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

    @Select("select * from order_detail where order_id=#{hotel_id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "customerId" ,column = "customer_id"),
            @Result(property = "orderNum" ,column = "order_num"),
            @Result(property = "orderContent",column = "order_content"),
            @Result(property = "orderPrice",column = "order_price"),
            @Result(property = "orderStatus",column = "order_status"),
            @Result(property = "orderId",column = "order_id"),
            @Result(property = "flag",column = "flag")
    })
    List<OrderDetail> selectOrderByHotelId(String hotel_id);

}
