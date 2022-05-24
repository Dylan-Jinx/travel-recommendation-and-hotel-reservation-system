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
    private Double parkRating;

    @ApiModelProperty("喜欢寺庙的评分")
    private Double templeRating;

    @ApiModelProperty("喜欢大众景点的评分")
    private Double wellKnownRating;

    @ApiModelProperty("喜欢商场的评分")
    private Double shoppingCenterRating;

    @ApiModelProperty("喜欢海滩的评分")
    private Double beachRating;

    @ApiModelProperty("喜欢动植物园的评分")
    private Double animalAndPlantRating;

}
