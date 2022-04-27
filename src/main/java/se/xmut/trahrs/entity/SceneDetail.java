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
 * 景点信息详细（主要是图片）
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@Getter
@Setter
@TableName("scene_detail")
@ApiModel(value = "SceneDetail对象", description = "景点信息详细（主要是图片）")
public class SceneDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("景区id")
    private String sceneId;

    @ApiModelProperty("图片路径")
    private String imgUrl;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;


}
