package se.xmut.trahrs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import se.xmut.trahrs.domain.model.OrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import se.xmut.trahrs.domain.vo.OrderDetailSaleVo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

    /**
     * 查询周销售额
     * @param time 当前时间
     * @return 周销售额
     */
    public Double weekSale(@Param("time")LocalDateTime time);

    /**
     * 查询周每天销售金额
     * @return 每天销售金额列表
     */
    public List<OrderDetailSaleVo> weekSaleEachDay();

    /**
     * 查询周每天酒店销量
     * @return 每天酒店销量列表
     */
    public List<OrderDetailSaleVo> weekHotelSaleEachDay();

    /**
     * 查询周每天景点销量
     * @return 每天景点销量列表
     */
    public List<OrderDetailSaleVo> weekSceneSaleEachDay();

    /**
     * 查询酒店周销量
     * @param time 当前时间
     * @return 酒店周销量
     */
    public Double weekHotelSale(@Param("time")LocalDateTime time);

    /**
     * 查询景点周销量
     * @param time 当前时间
     * @return 景点周销量
     */
    public Double weekSceneSale(@Param("time")LocalDateTime time);
}
