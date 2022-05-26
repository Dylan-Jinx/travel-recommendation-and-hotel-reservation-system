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
import org.springframework.stereotype.Component;

/**
 * <p>
 * 酒店评论
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */

@Getter
@Setter
@Accessors(chain = true)
@TableName("hotel_comment")
@ApiModel(value = "HotelComment对象", description = "酒店评论")
public class HotelComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


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

    @ApiModelProperty("此评论情感分析")
    private Double semantic;


}
