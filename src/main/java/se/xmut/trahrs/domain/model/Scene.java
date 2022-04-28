package se.xmut.trahrs.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 景点信息
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "Scene对象", description = "景点信息")
public class Scene implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("景点id")
    private String sceneId;

    @ApiModelProperty("景点名称")
    private String sceneName;

    @ApiModelProperty("景点所在地区（地区代码）")
    private Integer sceneArea;

    @ApiModelProperty("景区游玩费用")
    private Double scenePrice;

    @ApiModelProperty("酒店信息介绍")
    private String sceneInfo;

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


}
