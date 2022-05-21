package se.xmut.trahrs.util;

import cn.hutool.core.util.CoordinateUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.xmut.trahrs.domain.model.HotelInfo;
import se.xmut.trahrs.domain.model.Scene;
import se.xmut.trahrs.exception.GaoDeException;

import java.util.List;
import java.util.Map;

/**
 * @author breeze
 * @date 2022/5/20 20:39
 */
@Component
public class GaoDeMapUtils {

    private final static String KEY = YamlUtil.getStringByYaml("GaoDe.key");
    private final static String NEARBY_URL_V5 = "https://restapi.amap.com/v5/place/around?";
    private String type = "宾馆酒店";
    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param types 默认查周边宾馆酒店，如需查询别的周边信息，传递类型
     * @param location 经纬度
     * @param radius 查询范围
     * @param page_num 第几页
     * @return
     * @throws GaoDeException
     */
    public List<HotelInfo> getNearbyInfos(String types, String location, Integer radius, Integer page_num) throws GaoDeException {
        StringBuilder url = new StringBuilder(NEARBY_URL_V5);
        if(types != null && types.length()>0){
            type = types;
        }
        url.append("key=").append(KEY)
                .append("&types=").append(type)
                .append("&location=").append(location)
                .append("&radius=").append(radius)
                .append("&page_size=25").append("&page_num=").append(page_num);

        String res = HttpUtil.get(url.toString());
        JSONObject json = JSONUtil.parseObj(res);
        String info = json.getStr("info");

        if(info != null && "OK".equals(info)){
            String count = json.getStr("count");

            if("0".equals(count)){
                return null;
            }

            List<HotelInfo> infoList = json.getBeanList("pois", HotelInfo.class);

            return infoList;

        }else if(info != null && "DAILY_QUERY_OVER_LIMIT".equals(info)){
            throw new GaoDeException("This Key Daily Query Over Limit");
        }else {
            throw new GaoDeException(json.toString());
        }
    }

    /**
     * @param p1Lng
     * @param p1Lat
     * @param p2Lng
     * @param p2Lat
     * @return 两点距离
     */
    public double locationToDistance(double p1Lng, double p1Lat, double p2Lng, double p2Lat){
        //几何要素工厂
        GeometryFactory geometryFactory = new GeometryFactory();

        //高德坐标转GPS
        CoordinateUtil.Coordinate wgsP1 = CoordinateUtil.gcj02ToWgs84(p1Lng, p1Lat);
        CoordinateUtil.Coordinate wgsP2 = CoordinateUtil.gcj02ToWgs84(p2Lng, p2Lat);

        //转高斯
        double[] gaussP1 = wgs84ToGauss6(wgsP1.getLng(), wgsP1.getLat());
        double[] gaussP2 = wgs84ToGauss6(wgsP2.getLng(), wgsP2.getLat());

        Point p1 = geometryFactory.createPoint(new Coordinate(gaussP1[0], gaussP1[1]));
        Point p2 = geometryFactory.createPoint(new Coordinate(gaussP2[0], gaussP2[1]));

        return p1.distance(p2);
    }

    /**
     * 经纬度转高斯坐标
     * @param lng
     * @param lat
     * @return
     */
    public double[] wgs84ToGauss6(double lng, double lat) {
        int projNo = 0;
        //高斯6带
        int zoneWide = 6;
        double[] output = new double[2];
        double longitude1, latitude1, longitude0, X0, Y0, xval, yval;
        double a, f, e2, ee, NN, T, C, A, M, iPI;
        //3.1415926535898/180.0;
        iPI = 0.0174532925199433;
        a = 6378137.0;
        //WGS84坐标系参数
        f = 1.0 / 298.257223563;
        projNo = (int) (lng / zoneWide);
        longitude0 = (double)(projNo * zoneWide + zoneWide / 2);
        longitude0 = longitude0 * iPI;
        // 经度转换为弧度
        longitude1 = lng * iPI;
        // 纬度转换为弧度
        latitude1 = lat * iPI;
        e2 = 2 * f - f * f;
        ee = e2 / (1.0 - e2);
        NN = a
                / Math.sqrt(1.0 - e2 * Math.sin(latitude1)
                * Math.sin(latitude1));
        T = Math.tan(latitude1) * Math.tan(latitude1);
        C = ee * Math.cos(latitude1) * Math.cos(latitude1);
        A = (longitude1 - longitude0) * Math.cos(latitude1);
        M = a
                * ((1 - e2 / 4 - 3 * e2 * e2 / 64 - 5 * e2 * e2 * e2 / 256)
                * latitude1
                - (3 * e2 / 8 + 3 * e2 * e2 / 32 + 45 * e2 * e2 * e2
                / 1024) * Math.sin(2 * latitude1)
                + (15 * e2 * e2 / 256 + 45 * e2 * e2 * e2 / 1024)
                * Math.sin(4 * latitude1) - (35 * e2 * e2 * e2 / 3072)
                * Math.sin(6 * latitude1));
        // 因为是以赤道为Y轴的，与我们南北为Y轴是相反的，所以xy与高斯投影的标准xy正好相反;
        xval = NN
                * (A + (1 - T + C) * A * A * A / 6 + (5 - 18 * T + T * T + 14
                * C - 58 * ee)
                * A * A * A * A * A / 120);
        yval = M
                + NN
                * Math.tan(latitude1)
                * (A * A / 2 + (5 - T + 9 * C + 4 * C * C) * A * A * A * A / 24 + (61
                - 58 * T + T * T + 270 * C - 330 * ee)
                * A * A * A * A * A * A / 720);
        X0 = 1000000L * (projNo + 1) + 500000L;
        Y0 = 0;
        xval = xval + X0;
        yval = yval + Y0;
        output[0] = xval;
        output[1] = yval;
        return output;
    }
}
