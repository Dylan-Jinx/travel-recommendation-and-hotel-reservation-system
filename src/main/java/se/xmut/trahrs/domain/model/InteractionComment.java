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
 * 交流评论
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("interaction_comment")
@ApiModel(value = "InteractionComment对象", description = "交流评论")
public class InteractionComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String commentId;

    @ApiModelProperty("用户id")
    private String customerId;

    private String content;

    @ApiModelProperty("ip地址")
    private String ip;

    @ApiModelProperty("举报状态")
    private Integer reportStatus;

    private LocalDateTime createTime;

    private Integer flag;


}
