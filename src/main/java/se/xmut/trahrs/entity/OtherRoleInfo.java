package se.xmut.trahrs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 其他角色id（酒店、景区、餐馆、后台管理、）
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@Getter
@Setter
@TableName("other_role_info")
@ApiModel(value = "OtherRoleInfo对象", description = "其他角色id（酒店、景区、餐馆、后台管理、）")
public class OtherRoleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("角色id")
    private String roleId;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("密码")
    private String rolePwd;

    @ApiModelProperty("用户名称")
    private String roleName;

    @ApiModelProperty("关联id例如餐馆id酒店id景点id")
    private String relationId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("最后登录时间")
    private LocalDateTime lastLoginTime;


}
