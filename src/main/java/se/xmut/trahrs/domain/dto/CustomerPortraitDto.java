package se.xmut.trahrs.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author breeze
 * @date 2022/5/24 15:55
 */
@Getter
@Setter
public class CustomerPortraitDto {

    @ApiModelProperty("喜欢公园的评分")
    private Double 公园;

    @ApiModelProperty("喜欢寺庙的评分")
    private Double 寺庙;

    @ApiModelProperty("喜欢大众景点的评分")
    private Double 国家级景点;

    @ApiModelProperty("喜欢商场的评分")
    private Double 商场;

    @ApiModelProperty("喜欢海滩的评分")
    private Double 海滩;

    @ApiModelProperty("喜欢动植物园的评分")
    private Double 物园;

}
