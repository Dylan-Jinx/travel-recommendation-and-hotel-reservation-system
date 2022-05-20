package se.xmut.trahrs.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class HotelInfoDto implements Serializable {
    @ApiModelProperty("酒店名称")
    private String name;

    @ApiModelProperty("类型")
    @TableField("`type`")
    private String type;

    @ApiModelProperty("省")
    private String pName;

    @ApiModelProperty("市")
    private String cityName;

    @ApiModelProperty("区域")
    private String adName;

    @ApiModelProperty("联系电话")
    private String tel;

    @ApiModelProperty("酒店评分")
    private Float scale;

    @ApiModelProperty("开放时间")
    private String opentimeWeek;

    @ApiModelProperty("活动标签")
    private String recTag;

    @ApiModelProperty("分类标签")
    private String keyTag;

    @ApiModelProperty("酒店图片路径")
        private String photos;
}
