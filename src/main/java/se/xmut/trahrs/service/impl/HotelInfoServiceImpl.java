package se.xmut.trahrs.service.impl;

import se.xmut.trahrs.domain.model.HotelInfo;
import se.xmut.trahrs.mapper.HotelInfoMapper;
import se.xmut.trahrs.service.HotelInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
