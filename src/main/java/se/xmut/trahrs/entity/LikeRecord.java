package se.xmut.trahrs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 点赞记录
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@Getter
@Setter
@TableName("like_record")
@ApiModel(value = "LikeRecord对象", description = "点赞记录")
public class LikeRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("顾客id")
    private String customerId;

    @ApiModelProperty("什么评论的点赞id")
    private String recordId;


}
