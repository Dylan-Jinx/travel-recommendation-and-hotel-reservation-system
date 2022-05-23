package se.xmut.trahrs.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter


public class LikeRecordDto implements Serializable {
    @ApiModelProperty("顾客id")
    private String customerId;

    @ApiModelProperty("什么评论的点赞id")
    private String recordId;
}
