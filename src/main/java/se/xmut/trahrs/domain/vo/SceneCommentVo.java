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
public class SceneCommentVo {

    private static final long serialVersionUID = 1L;



    @ApiModelProperty("评论id")
    private String commentId;

    @ApiModelProperty("用户id")
    private String customerId;

    @ApiModelProperty("景点id")
    private String sceneId;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("几星评价")
    private Integer star;

    @ApiModelProperty("评论时间")
    private LocalDateTime commentTime;

    @ApiModelProperty("举报状态")
    private Integer reportStatus;

    @ApiModelProperty("图片路径")
    private String img;
    private Customer customer;
}
