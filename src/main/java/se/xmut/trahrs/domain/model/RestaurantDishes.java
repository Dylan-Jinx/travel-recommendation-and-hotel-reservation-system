package se.xmut.trahrs.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 餐馆菜品
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("restaurant_dishes")
@ApiModel(value = "RestaurantDishes对象", description = "餐馆菜品")
public class RestaurantDishes implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("菜品id")
    private Integer dishesId;

    @ApiModelProperty("餐馆id")
    private String restaurantId;

    @ApiModelProperty("菜品名")
    private String dishesName;

    @ApiModelProperty("菜品路径")
    private String dishesImg;

    @ApiModelProperty("菜品状态（无菜品或新菜品或正常状态）")
    private Integer dishesStatus;

    @ApiModelProperty("消费数量")
    private String saleCount;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;


}
