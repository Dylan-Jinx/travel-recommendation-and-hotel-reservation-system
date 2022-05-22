package se.xmut.trahrs.test.gaoDe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.xmut.trahrs.domain.model.HotelInfo;
import se.xmut.trahrs.exception.GaoDeException;
import se.xmut.trahrs.util.MapUtils;

import java.util.List;

/**
 * @author breeze
 * @date 2022/5/20 22:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapUtilsTest {

    @Autowired
    private MapUtils mapUtils;

    @Test
    public void getNearbyInfos() throws GaoDeException {
        List<HotelInfo> list = mapUtils.getNearbyInfosByGaoDe(null, "118.06702,24.444695", 1000, 1);
        for (HotelInfo hotelInfo : list){
            System.out.println(hotelInfo);
        }
    }

    @Test
    public void getLocationToDistance(){
        System.out.println(mapUtils.locationToDistance(118.06702, 24.444695, 118.062882, 24.572083));
    }
}