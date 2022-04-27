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
 * 交流记录
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@Getter
@Setter
@TableName("customer_interaction")
@ApiModel(value = "CustomerInteraction对象", description = "交流记录")
public class CustomerInteraction implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户id")
    private String customerId;

    @ApiModelProperty("主题类型")
    private Integer titleType;

    @ApiModelProperty("具体内容")
    private String content;

    @ApiModelProperty("举报状态")
    private Integer reportStatus;

    @ApiModelProperty("点赞")
    private Integer likeCount;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("ip地址")
    private String ip;

    private Integer flag;


}
