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
 * 公告轮播图
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@Getter
@Setter
@ApiModel(value = "Carousel对象", description = "公告轮播图")
public class Carousel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("图片路径")
    private String imgUrl;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;


}
