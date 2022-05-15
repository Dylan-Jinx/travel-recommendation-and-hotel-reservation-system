package se.xmut.trahrs.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class HotelInfoDto implements Serializable {
    @ApiModelProperty("酒店名称")
    private String hotelName;

    @ApiModelProperty("酒店介绍")
    private String hotelInfo;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("酒店评分")
    private Float scale;

    @ApiModelProperty("区域代码")
    private Integer area;

    @ApiModelProperty("具体地点")
    private String location;

    @ApiModelProperty("酒店图片路径")
        private String imgUrl;
}
