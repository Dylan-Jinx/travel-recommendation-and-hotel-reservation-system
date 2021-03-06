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

/**
 * <p>
 * 餐馆评论
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("restaurant_comment")
@ApiModel(value = "RestaurantComment对象", description = "餐馆评论")
public class RestaurantComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("评论id")
    private String commentId;

    @ApiModelProperty("用户id")
    private String customerId;

    @ApiModelProperty("餐厅id")
    private String restaurantId;

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

    @ApiModelProperty("编辑状态")
    private Integer editStatus;

    @ApiModelProperty("此评论情感分析")
    private Double semantic;

}
