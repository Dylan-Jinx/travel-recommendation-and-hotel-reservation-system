package se.xmut.trahrs.test.GaoDe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.xmut.trahrs.domain.model.HotelInfo;
import se.xmut.trahrs.exception.GaoDeException;
import se.xmut.trahrs.util.GaoDeMapUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author breeze
 * @date 2022/5/20 22:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GaoDeMapUtilsTest {

    @Autowired
    private GaoDeMapUtils gaoDeMapUtils;

    @Test
    public void getNearbyHotelInfos() throws GaoDeException {
        List<HotelInfo> list = gaoDeMapUtils.getNearbyInfos(null, "118.06702,24.444695", 1000, 1);
        for (HotelInfo hotelInfo : list){
            System.out.println(hotelInfo);
        }
    }

    @Test
    public void getLocationToDistance(){
        System.out.println(gaoDeMapUtils.locationToDistance(116.368904, 39.923423, 116.387271, 39.922501));
    }
}