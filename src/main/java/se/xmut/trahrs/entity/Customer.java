package se.xmut.trahrs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@Getter
@Setter
@ApiModel(value = "Customer对象", description = "用户")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id标识")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户id")
    private String customerId;

    @ApiModelProperty("用户名称")
    private String customerName;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("用户密码")
    private String customerPwd;

    @ApiModelProperty("性别")
    private Integer gender;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("职业")
    private Integer professsion;

    @ApiModelProperty("地区（这里使用区域代码）")
    private Integer area;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("最后登录时间")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty("最后登录ip")
    private String lastLoginIp;

    @ApiModelProperty("修改标志")
    private Integer removeFlag;


}
