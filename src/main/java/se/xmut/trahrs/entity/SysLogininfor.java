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
 * 系统访问记录
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@Getter
@Setter
@TableName("sys_logininfor")
@ApiModel(value = "SysLogininfor对象", description = "系统访问记录")
public class SysLogininfor implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("访问ID")
    @TableId(value = "info_id", type = IdType.AUTO)
    private Long infoId;

    @ApiModelProperty("登录账号")
    private String loginName;

    @ApiModelProperty("登录IP地址")
    private String ipaddr;

    @ApiModelProperty("登录地点")
    private String loginLocation;

    @ApiModelProperty("浏览器类型")
    private String browser;

    @ApiModelProperty("操作系统")
    private String os;

    @ApiModelProperty("提示消息")
    private String msg;

    @ApiModelProperty("访问时间")
    private LocalDateTime loginTime;


}
