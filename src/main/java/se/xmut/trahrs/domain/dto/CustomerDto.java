package se.xmut.trahrs.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CustomerDto implements Serializable {
    @ApiModelProperty("用户名称")
    private String customerName;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("用户密码")
    private String customerPwd;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("年龄")
    private String age;

    @ApiModelProperty("职业")
    private String professsion;

    @ApiModelProperty("地区（这里使用区域代码）")
    private String area;

    @ApiModelProperty("用户头像")
    private String avatar;

}
