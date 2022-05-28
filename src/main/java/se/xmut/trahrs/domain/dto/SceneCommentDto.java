package se.xmut.trahrs.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SceneCommentDto implements Serializable {

    @ApiModelProperty("景点主键id")
    private Long scenePK;

    @ApiModelProperty("用户主键id")
    private Long customerPK;

    @ApiModelProperty("景点id")
    private String sceneId;

    @ApiModelProperty("用户id")
    private String customerId;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("几星评价")
    private Integer star;


    @ApiModelProperty("图片路径")
    private String img;


}
