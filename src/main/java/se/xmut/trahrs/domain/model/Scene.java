package se.xmut.trahrs.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
 * @since 2022-05-19
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

    @ApiModelProperty("英文名")
    private String sceneEnglishName;

    @ApiModelProperty("标签")
    private String sceneTag;

    @ApiModelProperty("经度")
    private String sceneLongitude;

    @ApiModelProperty("纬度")
    private String sceneLatitude;

    @ApiModelProperty("评价分数")
    private Double sceneCommentLevel;

    @ApiModelProperty("成人票")
    private Integer sceneHumanPrice;

    @ApiModelProperty("老人票")
    private Integer sceneOldmanPrice;

    @ApiModelProperty("儿童票")
    private Integer sceneChildrenPrice;

    @ApiModelProperty("建议游玩时间")
    private String advicePlayTime;

    @ApiModelProperty("开放时间")
    private String openTime;

    @ApiModelProperty("介绍")
    private String sceneIntro;

    @ApiModelProperty("优惠政策")
    private String scenePreferential;

    @ApiModelProperty("展示图片")
    private String sceneShowImg;

    @ApiModelProperty("特点")
    private String sceneTrait;


}
