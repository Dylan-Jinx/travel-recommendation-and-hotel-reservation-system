package se.xmut.trahrs.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 作者
 * @since 2022-05-20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("hotel_info")
@ApiModel(value = "HotelInfo对象", description = "")
public class HotelInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("酒店id")
    private String hotelId;

    @ApiModelProperty("酒店名称")
    @TableField("`name`")
    private String name;

    @ApiModelProperty("酒店位置（经纬度）")
    private String location;

    @ApiModelProperty("类型")
    @TableField("`type`")
    private String type;

    @ApiModelProperty("省")
    private String pName;

    @ApiModelProperty("市")
    private String cityName;

    @ApiModelProperty("区域")
    private String adName;

    @ApiModelProperty("具体地址")
    private String address;

    @ApiModelProperty("省代码")
    private String pCode;

    @ApiModelProperty("城市代码")
    private String cityCode;

    @ApiModelProperty("区域代码")
    private String adCode;

    @ApiModelProperty("开放时间")
    private String opentimeWeek;

    @ApiModelProperty("电话")
    private String tel;

    @ApiModelProperty("活动标签")
    private String recTag;

    @ApiModelProperty("分类标签")
    private String keyTag;

    @ApiModelProperty("消费")
    private Integer cost;

    @ApiModelProperty("别名")
    private String alias;

    private String naviPoiid;

    private String entrLocation;

    private String exitLocation;

    private String gridCode;

    @ApiModelProperty("展示图片")
    private String photos;

    private Double rating;

}
