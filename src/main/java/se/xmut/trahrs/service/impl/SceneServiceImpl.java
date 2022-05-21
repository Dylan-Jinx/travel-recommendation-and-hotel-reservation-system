package se.xmut.trahrs.service.impl;

import cn.hutool.core.util.CoordinateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import se.xmut.trahrs.domain.model.HotelInfo;
import se.xmut.trahrs.domain.model.Scene;
import se.xmut.trahrs.exception.GaoDeException;
import se.xmut.trahrs.mapper.SceneMapper;
import se.xmut.trahrs.service.SceneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import se.xmut.trahrs.util.GaoDeMapUtils;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author breeze
 * @since 2022-05-20
 */
class Edges{
    int lng, lat, w;

    public Edges(String location, int w) {
        String[] lonAndLat = location.split(",");
        this.lng = Integer.parseInt(lonAndLat[0]);
        this.lat = Integer.parseInt(lonAndLat[1]);
        this.w = w;
    }
}


@Service
public class SceneServiceImpl extends ServiceImpl<SceneMapper, Scene> implements SceneService {

    @Autowired
    private GaoDeMapUtils gaoDeMapUtils;
    private Edges[][] edges;
    private final static double EARTH_RADIUS = 6378.137;

    @Override
    public Map<HotelInfo, Double> getNearestHotel(List<Scene> list, double radius) throws GaoDeException {
        Set<HotelInfo> hotelInfos = new HashSet<>();
        System.out.println(list.size());
        System.out.println(list);
        for (Scene scene : list) {
            int curPage = 1;
            long startTime = System.currentTimeMillis();
            List<HotelInfo> hotelList = gaoDeMapUtils.getNearbyInfos("宾馆酒店" , scene.getLocation(), (int) radius, curPage++);
            String[] location = scene.getLocation().split(",");
            double lng = Double.parseDouble(location[0]);
            double lat = Double.parseDouble(location[1]);

            CoordinateUtil.Coordinate pt = CoordinateUtil.gcj02ToWgs84(lng, lat);

            double maxLng = pt.getLng() + (radius * 0.00001141);
            double minLng = pt.getLng() - (radius * 0.00001141);
            double maxLat = pt.getLat() + (radius * 0.00000899);
            double minLat = pt.getLat() - (radius * 0.00000899);

            CoordinateUtil.Coordinate max = CoordinateUtil.wgs84ToGcj02(maxLng, maxLat);
            CoordinateUtil.Coordinate min = CoordinateUtil.wgs84ToGcj02(minLng, minLat);

            System.out.println(max);
            System.out.println(min);
            System.out.println(maxLng+","+maxLat);
            System.out.println(minLng+","+minLat);

            System.out.println(gaoDeMapUtils.locationToDistance(lng, lat, maxLng, maxLat));



            //获取到所有景点附近的酒店
            while(hotelList!=null){
                hotelInfos.addAll(hotelList);
                hotelList = gaoDeMapUtils.getNearbyInfos("宾馆酒店" , scene.getLocation(), (int) radius, curPage++);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        }
        for (HotelInfo h:
             hotelInfos) {
            System.out.println(h);
        }
        System.out.println("set "+hotelInfos.size());
        int num = hotelInfos.size() + list.size();
        edges = new Edges[num+1][num+1];
//            for (int i = 0; i < edges.length; i++) {
//                for (int j = 0; j < edges[0].length; j++) {
//                    edges[i][j].w = i==j ? 0 : 0x3f3f3f3f;
//                }
//            }
//            for (int i = 1; i <= list.size() ; i++) {
//                edges[i]
//            }
        //计算各个酒店到各个景点的距离
        for (int i = 0; i < hotelInfos.size(); i++) {
            for (int j = 0; j < list.size(); j++) {

            }
        }
        return null;
    }

    public void floyd(int total, int sceneNum){
        for(int k=1;k<=total;k++){
            for(int i=1;i<=total;i++){
                for(int j=1;j<=total;j++){
                    edges[i][j].w = Math.min(edges[i][j].w, edges[i][k].w+edges[k][j].w);
                }
            }
        }
    }
}
