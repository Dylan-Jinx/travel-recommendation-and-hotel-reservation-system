package se.xmut.trahrs.domain.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerInteractionDto implements Serializable {
    @ApiModelProperty("用户id")
    private String customerId;


    @ApiModelProperty("具体内容")
    private String content;

    @ApiModelProperty("图片")
    private String img;

    @ApiModelProperty("标题")
    private String title;

}
