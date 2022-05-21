package se.xmut.trahrs.service.impl;

import cn.hutool.core.util.CoordinateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import se.xmut.trahrs.domain.model.HotelInfo;
import se.xmut.trahrs.domain.model.Scene;
import se.xmut.trahrs.exception.GaoDeException;
import se.xmut.trahrs.mapper.HotelInfoMapper;
import se.xmut.trahrs.mapper.SceneMapper;
import se.xmut.trahrs.service.HotelInfoService;
import se.xmut.trahrs.service.SceneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import se.xmut.trahrs.util.GaoDeMapUtils;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author breeze
 * @since 2022-05-20
 */

@Service
public class SceneServiceImpl extends ServiceImpl<SceneMapper, Scene> implements SceneService {

    @Autowired
    private GaoDeMapUtils gaoDeMapUtils;
    @Autowired
    private HotelInfoService hotelInfoService;
    @Autowired
    private HotelInfoMapper hotelInfoMapper;

    @Override
    public List<Object[]> getNearestHotel(List<Scene> list, double radius) throws GaoDeException {

        List<Object[]> ans = new ArrayList<>();
        Set<HotelInfo> hotelInfos = new HashSet<>();

        //添加范围内的酒店
        for (Scene scene : list) {
            int curPage = 1;

            String[] location = scene.getLocation().split(",");
            double lng = Double.parseDouble(location[0]);
            double lat = Double.parseDouble(location[1]);

            double maxLng = lng + (radius * 0.00001141);
            double minLng = lng - (radius * 0.00001141);
            double maxLat = lat + (radius * 0.00000899);
            double minLat = lat - (radius * 0.00000899);

            hotelInfos.addAll(hotelInfoMapper.findNearbyHotel(maxLng + "", minLng + "", maxLat + "", minLat + ""));
        }

        //获取景点位置
        for (HotelInfo h:hotelInfos) {
            String[] hL = h.getLocation().split(",");
            double hLng = Double.parseDouble(hL[0]);
            double hLat = Double.parseDouble(hL[1]);
            double len = 0;
            for (Scene scene:list) {
                String[] location = scene.getLocation().split(",");
                double lng = Double.parseDouble(location[0]);
                double lat = Double.parseDouble(location[1]);
                len += gaoDeMapUtils.locationToDistance(lng, lat, hLng, hLat);
            }
            ans.add(new Object[]{h, len});
        }

        //按照距离和从近到远排序
        ans.sort((a,b)->{
            return (int) (((double)a[1]) - ((double)b[1]));
        });

        return ans;
    }

    @Override
    public List<Object[]> getNearbyHotelByScene(Scene scene, double radius) throws GaoDeException {

        List<Object[]> ans = new ArrayList<>();
        Set<HotelInfo> set = new HashSet<>();

        int curPage = 1;

        List<HotelInfo> tempList = gaoDeMapUtils.getNearbyInfos("宾馆酒店", scene.getLocation(), (int) radius, curPage++);
        //获取到所有景点附近的酒店
        while (tempList != null) {
            set.addAll(tempList);
            tempList = gaoDeMapUtils.getNearbyInfos("宾馆酒店", scene.getLocation(), (int) radius, curPage++);
        }

        String[] location = scene.getLocation().split(",");
        double lng = Double.parseDouble(location[0]);
        double lat = Double.parseDouble(location[1]);
        for (HotelInfo h : set) {
            String[] hL = h.getLocation().split(",");
            double hLng = Double.parseDouble(hL[0]);
            double hLat = Double.parseDouble(hL[1]);
            ans.add(new Object[]{h, gaoDeMapUtils.locationToDistance(lng, lat, hLng, hLat)});

        }
        return ans;
    }

}
