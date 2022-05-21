package se.xmut.trahrs.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
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
@Repository
public interface HotelInfoMapper extends BaseMapper<HotelInfo> {
    /**
     * 找附近酒店
     * @param maxLng
     * @param minLng
     * @param maxLat
     * @param minLat
     * @return 范围内的酒店
     */
    @Select("select * from hotel_info where" +
            " SUBSTRING_INDEX(location,',',1) <= #{maxLng} and" +
            " SUBSTRING_INDEX(location,',',1) >= #{minLng} and" +
            " SUBSTRING_INDEX(location,',',-1) <= #{maxLat} and" +
            " SUBSTRING_INDEX(location,',',-1) >= #{minLat}")
    List<HotelInfo> findNearbyHotel(@Param("maxLng") String maxLng,
                                 @Param("minLng") String minLng,
                                 @Param("maxLat") String maxLat,
                                 @Param("minLat") String minLat);
}
