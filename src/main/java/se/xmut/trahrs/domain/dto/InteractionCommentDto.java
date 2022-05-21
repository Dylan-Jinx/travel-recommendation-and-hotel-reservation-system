package se.xmut.trahrs.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class InteractionCommentDto implements Serializable {
    @ApiModelProperty("用户id")
    private String customerId;

    private String content;


}
