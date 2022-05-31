package se.xmut.trahrs.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

/**
 * @author breeze
 * @date 2022/5/31 18:04
 */
@Getter@Setter
@Accessors(chain = true)
@ApiModel(value = "OrderDetailSaleVo对象", description = "传递销售金额数据")
public class OrderDetailSaleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("日期")
    private Date date;

    @ApiModelProperty("当日销售金额/销量")
    private Double value;

}
