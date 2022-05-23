package se.xmut.trahrs.service;

import se.xmut.trahrs.domain.model.HotelInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-05-20
 */
public interface HotelInfoService extends IService<HotelInfo> {
    List<HotelInfo> findHotelByTypeAndKeyTagAndName(String type, String key_tag, String name_brand);
}
