package se.xmut.trahrs;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.xmut.trahrs.domain.model.HotelInfo;
import se.xmut.trahrs.service.HotelInfoService;

import java.util.*;

@SpringBootTest
class TravelRecommendationAndHotelReservationSystemApplicationTests {

    @Autowired
    private HotelInfoService hotelInfoService;
    @Test
    void contextLoads() {
        Random random = new Random();

        List<HotelInfo> hotelInfos = hotelInfoService.list();

        for (HotelInfo hotelInfo:hotelInfos) {
            List<Integer> integerList = new ArrayList<>();
            integerList.add((random.nextInt(30)+10)*10+9+random.nextInt(9)*10);
            integerList.add((((random.nextInt(20)+10)*random.nextInt(50)+10)/10)*10+100+9+random.nextInt(9)*10);
            integerList.add((random.nextInt(50)+20)*10+9+random.nextInt(9)*10);
//            System.out.println(hotelInfo);
            Collections.sort(integerList);
            String s = hotelInfo.getKeyTag();
            if(s.contains("星") || s.contains("豪") || s.contains("高")){
                hotelInfo.setCost(integerList.get(2));
            } else if ( s.contains("商") || s.contains("主")) {
                hotelInfo.setCost(integerList.get(1));
            }else {
                hotelInfo.setCost(integerList.get(0));
            }
            hotelInfoService.getBaseMapper().updateById(hotelInfo);
        }

    }

}
