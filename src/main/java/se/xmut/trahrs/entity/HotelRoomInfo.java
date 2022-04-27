package se.xmut.trahrs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 酒店房间信息
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@Getter
@Setter
@TableName("hotel_room_info")
@ApiModel(value = "HotelRoomInfo对象", description = "酒店房间信息")
public class HotelRoomInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("酒店id")
    private String hotelId;

    @ApiModelProperty("房间id")
    private String roomId;

    @ApiModelProperty("类型id")
    private Integer roomTypeId;

    @ApiModelProperty("图片路径")
    private String imgUrl;

    @ApiModelProperty("剩余数量")
    private Integer remainCount;

    @ApiModelProperty("价格")
    private Float price;


}
