package se.xmut.trahrs.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class HotelCommentDto implements Serializable {
    @ApiModelProperty("用户id")
    private String customerId;

    @ApiModelProperty("酒店id")
    private String hotelId;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("几星评价")
    private Integer star;
    
    @ApiModelProperty("图片路径")
    private String imgUrl;

}
