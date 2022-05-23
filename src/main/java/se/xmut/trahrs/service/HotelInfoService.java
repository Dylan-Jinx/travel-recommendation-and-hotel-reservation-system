package se.xmut.trahrs.service;

import se.xmut.trahrs.domain.model.HotelInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import se.xmut.trahrs.domain.model.HotelRoomInfo;
import se.xmut.trahrs.domain.vo.HotelInfoVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-05-20
 */
public interface HotelInfoService extends IService<HotelInfo> {

    /**
     * HotelInfoVo分页助手
     * @param hotelInfoVoList 需要被分页的HotelInfoVo
     * @return 分页完成的map
     */
    public Map<String, Object> HotelInfoVoPage(List<HotelInfoVo> hotelInfoVoList, Integer pageNum, Integer pageSize);
}
