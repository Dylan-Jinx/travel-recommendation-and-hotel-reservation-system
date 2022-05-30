package se.xmut.trahrs.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class OrderDetailDto implements Serializable {
    @ApiModelProperty("顾客id")
    private String customerId;

    @ApiModelProperty("订单信息")
    private String orderContent;

    @ApiModelProperty("订单数量")
    private Integer orderPrice;

    @ApiModelProperty("酒店景点uuid集合")
    private List<String> orderIdList;

    @ApiModelProperty("景点的长度")
    private Integer len;
}
