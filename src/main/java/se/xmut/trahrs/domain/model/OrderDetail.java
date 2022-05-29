package se.xmut.trahrs.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("order_detail")
@ApiModel(value = "OrderDetail对象", description = "订单")
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("顾客id")
    private String customerId;

    @ApiModelProperty("订单流水号")
    private String orderNum;

    @ApiModelProperty("订单信息")
    private String orderContent;

    @ApiModelProperty("订单数量")
    private Integer orderPrice;

    @ApiModelProperty("订单状态")
    private Integer orderStatus;

    @ApiModelProperty("订单创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("酒店id 或 景点id")
    private String orderId;


    @ApiModelProperty("0表示酒店的订单 1表示景点订单")
    private Integer flag;


}
