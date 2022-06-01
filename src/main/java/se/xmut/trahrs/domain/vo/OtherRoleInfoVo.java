package se.xmut.trahrs.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author breeze
 * @date 2022/6/1 17:58
 */
@Getter
@Setter
public class OtherRoleInfoVo implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("角色id")
    private String roleId;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("用户名称")
    private String roleName;

    @ApiModelProperty("关联id例如餐馆id酒店id景点id")
    private String relationId;
}
