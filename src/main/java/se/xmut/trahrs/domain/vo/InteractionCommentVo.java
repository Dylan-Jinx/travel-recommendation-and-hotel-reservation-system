package se.xmut.trahrs.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import se.xmut.trahrs.domain.model.Customer;

import java.time.LocalDateTime;
@Getter
@Setter
public class InteractionCommentVo {

    private static final long serialVersionUID = 1L;


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

    private String interactionId;
    private Customer customer;
}
