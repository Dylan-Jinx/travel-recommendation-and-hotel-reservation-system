package se.xmut.trahrs.domain.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelRoomInfoDto {

    @ApiModelProperty("酒店id")
    private String hotelId;

    @ApiModelProperty("房间id")
    private String roomId;

    @ApiModelProperty("类型id")
    private Integer roomTypeId;

    @ApiModelProperty("图片路径")
    private String imgUrl;

    @ApiModelProperty("价格")
    private Float price;

}
