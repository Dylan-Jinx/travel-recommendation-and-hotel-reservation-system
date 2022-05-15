package se.xmut.trahrs.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CustomerVo implements Serializable {
    @ApiModelProperty("用户id")
    private String customerId;

    @ApiModelProperty("用户名称")
    private String customerName;

    @ApiModelProperty("用户头像")
    private String avatar;
}
