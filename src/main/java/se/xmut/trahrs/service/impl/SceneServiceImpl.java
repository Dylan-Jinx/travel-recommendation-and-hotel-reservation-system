package se.xmut.trahrs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.domain.model.Customer;
import se.xmut.trahrs.domain.model.HotelInfo;
import se.xmut.trahrs.domain.model.Scene;
import se.xmut.trahrs.domain.vo.HotelInfoVo;
import se.xmut.trahrs.exception.GaoDeException;
import se.xmut.trahrs.mapper.HotelInfoMapper;
import se.xmut.trahrs.mapper.SceneMapper;
import se.xmut.trahrs.service.HotelInfoService;
import se.xmut.trahrs.service.SceneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import se.xmut.trahrs.util.MapUtils;

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
    private MapUtils mapUtils;
    @Autowired
    private HotelInfoService hotelInfoService;
    @Autowired
    private HotelInfoMapper hotelInfoMapper;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BloomFilterRedisServiceImpl bloomFilterRedisService;
    @Autowired
    private SceneMapper sceneMapper;

    @Override
    public List<HotelInfoVo> getNearestHotel(List<Scene> list, Double radius) {

        List<HotelInfoVo> ans = new ArrayList<>();
        Set<HotelInfo> hotelInfos = new HashSet<>();

        //添加范围内的酒店
        for (Scene scene : list) {
            String[] location = mapUtils.locationToMaxAndMinLngAndLatInString(scene.getLocation(), radius);

            hotelInfos.addAll(hotelInfoMapper.findNearbyHotel(location[0], location[1], location[2], location[3]));
        }

        //获取景点位置
        for (HotelInfo h : hotelInfos) {
            String[] hL = h.getLocation().split(",");
            double hLng = Double.parseDouble(hL[0]);
            double hLat = Double.parseDouble(hL[1]);
            double len = 0, nearestDis = Integer.MAX_VALUE;
            Scene nearestScene = null;
            for (Scene scene : list) {
                String[] location = scene.getLocation().split(",");
                double lng = Double.parseDouble(location[0]);
                double lat = Double.parseDouble(location[1]);
                double curLen = mapUtils.locationToDistance(lng, lat, hLng, hLat);
                len += curLen;
                if (nearestDis > curLen) {
                    nearestDis = curLen;
                    nearestScene = scene;
                }
            }
            HotelInfoVo temp = modelMapper.map(h, HotelInfoVo.class);
            temp.setSumDistance(len);
            if (nearestDis != Integer.MAX_VALUE) {
                temp.setNearestDistance(nearestDis);
                temp.setNearestScene(nearestScene);
            }
            temp.setNearestDistance(nearestDis);
            ans.add(temp);
        }

        //按照距离和从近到远排序
        ans.sort((a, b) -> {
            return (int) (a.getSumDistance() - b.getSumDistance());
        });
        return ans;
    }

    @Override
    public List<HotelInfoVo> getNearbyHotelByScene(Scene scene, Double radius) {

        List<HotelInfoVo> ans = new ArrayList<>();

        String[] location = mapUtils.locationToMaxAndMinLngAndLatInString(scene.getLocation(), radius);

        List<HotelInfo> tempList = hotelInfoMapper.findNearbyHotel(location[0], location[1], location[2], location[3]);

        double lng = mapUtils.locationToLngAndLatInDouble(scene.getLocation())[0];
        double lat = mapUtils.locationToLngAndLatInDouble(scene.getLocation())[1];
        for (HotelInfo h : tempList) {
            double hLng = mapUtils.locationToLngAndLatInDouble(h.getLocation())[0];
            double hLat = mapUtils.locationToLngAndLatInDouble(h.getLocation())[1];
            HotelInfoVo temp = modelMapper.map(h, HotelInfoVo.class);
            temp.setSumDistance(mapUtils.locationToDistance(lng, lat, hLng, hLat));
            ans.add(temp);
        }

        //按照距离和从近到远排序
        ans.sort((a, b) -> {
            return (int) ((a.getSumDistance() * 100) - (b.getSumDistance()) * 100);
        });

        return ans;
    }

    @Override
    public List<HotelInfoVo> getSceneNearbyHotelWithComprehensiveRecommendation(List<HotelInfoVo> nearestHotel) {

        Double lastDis = nearestHotel.get(nearestHotel.size() - 1).getSumDistance();
        List<String> nameList = new ArrayList<>();

        for (HotelInfoVo objs : nearestHotel) {
            nameList.add(objs.getName());
        }

        Double avgRating = hotelInfoMapper.findAvgRatingInRange(nameList);

        for (int i = 0; i < nearestHotel.size(); i++) {

            Double curDis = nearestHotel.get(i).getSumDistance();
            Double rating = nearestHotel.get(i).getRating();

            if (rating != null) {
                nearestHotel.get(i).setComprehensiveRating((lastDis / curDis) * 0.8 + rating * 0.2);
            } else {
                nearestHotel.get(i).setComprehensiveRating((lastDis / curDis) * 0.8 + (avgRating - 0.15) * 0.2);
            }
        }
        //按照综合分数从大到小排序
        nearestHotel.sort((a, b) -> {
            return (int) (b.getComprehensiveRating() * 100000 - (a.getComprehensiveRating() * 100000));
        });

        return nearestHotel;
    }

    @Override
    public List<HotelInfoVo> getSceneNearbyHotelWithHighestRatingRecommendation(List<HotelInfoVo> nearestHotel) {
        nearestHotel.sort(Comparator.comparing(HotelInfoVo::getRating, Comparator.nullsFirst(Double::compareTo)).reversed());
        return nearestHotel;
    }

    @Override
    public List<HotelInfoVo> getSceneNearbyHotelWithLowestRatingRecommendation(List<HotelInfoVo> nearestHotel) {
        nearestHotel.sort(Comparator.comparing(HotelInfoVo::getRating, Comparator.nullsLast(Double::compareTo)));
        return nearestHotel;
    }

    @Override
    public List<HotelInfoVo> getSceneNearbyHotelWithHighestPriceRecommendation(List<HotelInfoVo> nearestHotel) {
        nearestHotel.sort(Comparator.comparing(HotelInfoVo::getCost, Comparator.nullsFirst(Integer::compareTo)).reversed());
        return nearestHotel;
    }

    @Override
    public List<HotelInfoVo> getSceneNearbyHotelWithLowestPriceRecommendation(List<HotelInfoVo> nearestHotel) {
        nearestHotel.sort(Comparator.comparing(HotelInfoVo::getCost, Comparator.nullsLast(Integer::compareTo)));
        return nearestHotel;
    }

    @Override
    public List<Scene> bindSceneByUUID(List<Scene> scenes) {
        QueryWrapper<Scene> queryWrapper = new QueryWrapper<>();
        for (Scene scene : scenes) {
            queryWrapper.clear();
            queryWrapper.eq("scene_id", scene.getSceneId());
            BeanUtil.copyProperties(this.getOne(queryWrapper), scene);
        }
        return scenes;
    }

    @Override
    public Map<String, Object> myPage(List<Scene> sceneList, Integer pageNum, Integer pageSize, Long total) {
        Map<String, Object> map = new HashMap<>();

        if(total==null){
            total = this.count();
        }

        map.put("records", sceneList);
        map.put("total", total);
        map.put("size", pageSize);
        map.put("current", pageNum);
        map.put("pages", total % pageSize == 0 ? total / pageSize : total / pageSize + 1);

        return map;
    }

    @Override
    public List<String> getCustomerPortraitTypeList(JSONObject jsonObject) {
        List<String> typeList = new ArrayList<>();

        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if (Double.parseDouble(entry.getValue().toString()) > 0.5) {
                typeList.add(entry.getKey());
            }
        }

        return typeList;
    }

    @Override
    public void loopCheckBloomFilter(List<Scene> list, List<Scene> res, Customer customer) {

        for (Scene scene : list) {
            //没推荐过
            if (!bloomFilterRedisService.includeByBloomFilter(
                    String.valueOf(customer.getId()), String.valueOf(scene.getId()))) {
                bloomFilterRedisService.addByBloomFilter(String.valueOf(customer.getId()),
                        String.valueOf(scene.getId()), null);
                res.add(scene);
                if (res.size() >= 3) {
                    break;
                }
            }
        }
    }

    @Override
    public List<Scene> getEnoughGuessByCustomerPortraitAndRating(List<String> typeList, List<Long> recommendItems, Customer customer) {
        List<Scene> res = new ArrayList<>();
        QueryWrapper<Scene> queryWrapper = new QueryWrapper<>();
        if (recommendItems != null) {
            queryWrapper.in("id", recommendItems);
            queryWrapper.orderByDesc("rating");
            res = sceneMapper.selectList(queryWrapper);
        }

        List<Scene> appendList = sceneMapper.getByType(typeList);

        loopCheckBloomFilter(appendList, res, customer);

        //如果用户画像推荐完了还是不够，再去找按rating排的
        while (res.size() < 3) {
            QueryWrapper<Scene> ratingPage = new QueryWrapper<>();
            ratingPage.orderByDesc("rating");
            int pageNum = 1, pageSize = 50;
            List<Scene> ratingScenes = page(new Page<>(pageNum, pageSize), ratingPage).getRecords();
            pageNum++;

            loopCheckBloomFilter(ratingScenes, res, customer);
        }
        return res;

    }
}
