package se.xmut.trahrs.domain.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import se.xmut.trahrs.domain.model.Customer;

import java.time.LocalDateTime;
@Getter
@Setter

@Accessors(chain = true)
public class HotelCommentVo {

    private static final long serialVersionUID = 1L;




    private String commentId;

    @ApiModelProperty("用户id")
    private String customerId;

    @ApiModelProperty("酒店id")
    private String hotelId;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("几星评价")
    private Integer star;

    @ApiModelProperty("评论时间")
    private LocalDateTime commentTime;

    @ApiModelProperty("举报状态")
    private Integer reportStatus;

    @ApiModelProperty("图片路径")
    private String imgUrl;
    private Customer customer;
}
