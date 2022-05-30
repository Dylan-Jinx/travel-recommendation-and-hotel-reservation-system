package se.xmut.trahrs.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class OrderDetailDto implements Serializable {
    @ApiModelProperty("顾客id")
    private String customerId;

    @ApiModelProperty("订单信息")
    private String orderContent;

    @ApiModelProperty("订单数量")
    private Integer orderPrice;

    @ApiModelProperty("酒店id 或 景点id")
    private String orderId;

}
