package se.xmut.trahrs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import se.xmut.trahrs.domain.model.HotelInfo;
import se.xmut.trahrs.mapper.HotelInfoMapper;
import se.xmut.trahrs.service.HotelInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-05-20
 */
@Service
public class HotelInfoServiceImpl extends ServiceImpl<HotelInfoMapper, HotelInfo> implements HotelInfoService {

    @Autowired
    HotelInfoMapper hotelInfoMapper;

    @Override
    public List<HotelInfo> findHotelByTypeAndKeyTagAndName(String type, String key_tag, String name_brand) {
        List<HotelInfo> hotelInfoList=hotelInfoMapper.findHotelByTypeAndKeyTagAndName(type,key_tag,name_brand);
        return hotelInfoList;
    }
}
