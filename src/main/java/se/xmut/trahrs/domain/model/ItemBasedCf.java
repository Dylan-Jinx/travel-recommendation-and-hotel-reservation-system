package se.xmut.trahrs.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 
 * </p>
 *
 * @author 作者
 * @since 2022-05-25
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("item_based_cf")
@ApiModel(value = "ItemBasedCf对象", description = "")
public class ItemBasedCf implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    private Long customerId;

    @ApiModelProperty("景点id")
    private Long sceneId;

    @ApiModelProperty("推荐值")
    @TableField("`value`")
    private Float value;

    @ApiModelProperty("时间戳")
    @TableField("`timestamp`")
    private LocalDateTime timestamp;


}
