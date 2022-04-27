package se.xmut.trahrs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 餐馆信息
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@Getter
@Setter
@ApiModel(value = "Restaurant对象", description = "餐馆信息")
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("餐厅id")
    private String restaurantId;

    @ApiModelProperty("餐厅名称")
    private String restaurantName;

    @ApiModelProperty("餐厅所在地区（地区代码）")
    private Integer restaurantArea;

    @ApiModelProperty("餐厅人均费用")
    private Integer restaurantPrice;

    @ApiModelProperty("餐厅信息介绍")
    private String restaurantInfo;

    @ApiModelProperty("点击次数")
    private Long viewCount;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("开放时间")
    private String openTime;

    @ApiModelProperty("实际位置")
    private String location;

    @ApiModelProperty("审核状态")
    private Integer checkStatus;

    @ApiModelProperty("图片路径")
    private String imgUrl;

    @ApiModelProperty("餐厅评分")
    private Float scale;


}
