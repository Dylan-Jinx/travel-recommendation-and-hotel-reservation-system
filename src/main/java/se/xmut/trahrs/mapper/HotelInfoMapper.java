package se.xmut.trahrs.mapper;

import org.apache.ibatis.annotations.*;
import se.xmut.trahrs.domain.model.HotelInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-05-20
 */
@Mapper
public interface HotelInfoMapper extends BaseMapper<HotelInfo> {
    /**
     * 找附近酒店
     * @param maxLng
     * @param minLng
     * @param maxLat
     * @param minLat
     * @return 范围内的酒店
     */
    List<HotelInfo> findNearbyHotel(@Param("maxLng") String maxLng,
                                 @Param("minLng") String minLng,
                                 @Param("maxLat") String maxLat,
                                 @Param("minLat") String minLat);

    /**
     * 计算范围内的均分
     * @return 返回范围内的均分
     */
    Double findAvgRatingInRange(List<String> nameList);


    /**
     * type 为查找酒店星级
     * key_tag 为酒店标签
     * name_brand 为酒店品牌
     */
    List<HotelInfo> findHotelByTypeAndKeyTagAndName(String type,String key_tag,String name_brand);


    @Select("select * from hotel_info where hotel_id=#{hotel_id}")
    @Results({
        @Result(id = true,property = "id",column = "id"),
        @Result(property = "hotelId",column = "hotel_id"),
        @Result(property = "name",column = "name"),
        @Result(property = "orderDetailList",
                column = "hotel_id",
                many = @Many(select = "se.xmut.trahrs.mapper.OrderDetailMapper.selectOrderByHotelId")
        )
    })
    List<HotelInfo> findByOrderCount(@Param("hotel_id") String order_id);




}
