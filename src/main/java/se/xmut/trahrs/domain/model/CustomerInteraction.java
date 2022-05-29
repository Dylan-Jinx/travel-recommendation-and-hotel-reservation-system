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
 * 交流记录
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("customer_interaction")
@ApiModel(value = "CustomerInteraction对象", description = "交流记录")
public class CustomerInteraction implements Serializable {

    private static final long serialVersionUID = 1L;
    private Customer customer;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户id")
    private String customerId;


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

    @ApiModelProperty("图片")
    private String img;

    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("标题id")
    private String titleId;


}
