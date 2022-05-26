package se.xmut.trahrs.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author 作者
 * @since 2022-05-19
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "Customer对象", description = "用户")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<CustomerInteraction> customerInteractionList;
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
    private String gender;

    @ApiModelProperty("身份证")
    private String identity;

    @ApiModelProperty("职业")
    private String profession;

    @ApiModelProperty("地区（这里使用区域代码）")
    private String area;

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

    @ApiModelProperty("用户画像，用于推荐，存JSON")
    private String customerPortrait;


}
