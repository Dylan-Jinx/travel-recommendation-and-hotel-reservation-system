
package se.xmut.trahrs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import se.xmut.trahrs.domain.model.HotelInfo;
import se.xmut.trahrs.domain.vo.HotelInfoVo;
import se.xmut.trahrs.mapper.HotelInfoMapper;
import se.xmut.trahrs.service.HotelInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, Object> HotelInfoVoPage(List<HotelInfoVo> hotelInfoVoList, Integer pageNum, Integer pageSize) {

        List<HotelInfoVo> hotelInfoPage =  hotelInfoVoList.subList((pageNum-1)*pageSize, (pageNum-1)*pageSize+pageSize);

        Map<String, Object> map = new HashMap<>();
        map.put("records", hotelInfoPage);
        map.put("total", hotelInfoVoList.size());
        map.put("size", pageSize);
        map.put("current", pageNum);
        map.put("pages", hotelInfoVoList.size()%pageSize==0 ? hotelInfoVoList.size()/pageSize : hotelInfoVoList.size()/pageSize +1);

        return map;
    }

    @Override
    public List<HotelInfo> findHotelByTypeAndKeyTagAndName(String type, String key_tag, String name_brand) {
        List<HotelInfo> hotelInfoList=hotelInfoMapper.findHotelByTypeAndKeyTagAndName(type,key_tag,name_brand);
        return hotelInfoList;
    }
}








