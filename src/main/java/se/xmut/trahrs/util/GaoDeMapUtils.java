package se.xmut.trahrs.util;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.xmut.trahrs.domain.model.HotelInfo;
import se.xmut.trahrs.exception.GaoDeException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author breeze
 * @date 2022/5/20 20:39
 */
@Component
public class GaoDeMapUtils {

    private final static String key = YamlUtil.getStringByYaml("GaoDe.key");
    private final static String nearbyUrlV5 = "https://restapi.amap.com/v5/place/around?";
    private String type = "宾馆酒店";
    @Autowired
    private ModelMapper modelMapper;

    public List<HotelInfo> getNearbyHotelInfos(String types, String location, Integer radius, Integer page_num) throws GaoDeException {
        StringBuilder url = new StringBuilder(nearbyUrlV5);
        if(types != null && types.length()>0){
            type = types;
        }
        url.append("key=").append(key)
                .append("&types=").append(type)
                .append("&location=").append(location)
                .append("&radius=").append(radius)
                .append("&page_size=25").append("&page_num=").append(page_num);
        System.out.println(url);

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
}
