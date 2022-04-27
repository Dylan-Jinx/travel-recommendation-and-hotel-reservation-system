package se.xmut.trahrs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 酒店信息
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@Getter
@Setter
@TableName("hotel_info")
@ApiModel(value = "HotelInfo对象", description = "酒店信息")
public class HotelInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("酒店id")
    private String hotelId;

    @ApiModelProperty("酒店名称")
    private String hotelName;

    @ApiModelProperty("酒店介绍")
    private String hotelInfo;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("酒店评分")
    private Float scale;

    @ApiModelProperty("点击次数")
    private Long viewCount;

    @ApiModelProperty("区域代码")
    private Integer area;

    @ApiModelProperty("具体地点")
    private String location;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("酒店图片路径")
    private String imgUrl;


}
