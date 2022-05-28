package se.xmut.trahrs.service;

import cn.hutool.json.JSONObject;
import se.xmut.trahrs.domain.model.Customer;
import se.xmut.trahrs.domain.model.HotelInfo;
import se.xmut.trahrs.domain.model.Scene;
import com.baomidou.mybatisplus.extension.service.IService;
import se.xmut.trahrs.domain.vo.HotelInfoVo;
import se.xmut.trahrs.exception.GaoDeException;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author breeze
 * @since 2022-05-20
 */
public interface SceneService extends IService<Scene> {

    /**
     * 获取景点列表中距离和最近的酒店
     * @param list 用户选择想去的景点
     * @param radius 单位米
     * @return 返回的酒店集合由距离从近到远排序，Vo中包含中可用的距离和属性
     */
    public List<HotelInfoVo> getNearestHotel(List<Scene> list, Double radius);

    /**
     * 根据景点查询附近酒店
     * @param scene 景点
     * @param radius 范围
     * @return 查询集 Vo中包含可用的距离和属性
     */
    public List<HotelInfoVo> getNearbyHotelByScene(Scene scene, Double radius);

    /**
     * 最近酒店列表按综合推荐排序
     * @param nearestHotel 最近的酒店列表 由getNearestHotel通过景点列表查出
     * @return 加权推荐的综合 Vo中包含可用的距离和以及综合评分属性
     */
    public List<HotelInfoVo> getSceneNearbyHotelWithComprehensiveRecommendation(List<HotelInfoVo> nearestHotel);

    /**
     * 按评分从高到低排序，没有评分的酒店排在最后，按远近排序
     * @param nearestHotel 最近的酒店列表 由getNearestHotel通过景点列表查出
     * @return 按评分从高到低排序
     */
    public List<HotelInfoVo> getSceneNearbyHotelWithHighestRatingRecommendation(List<HotelInfoVo> nearestHotel);

    /**
     * 按评分从低到高排序，没有评分的酒店排在最后，按远近排序
     * @param nearestHotel 最近的酒店列表 由getNearestHotel通过景点列表查出
     * @return 按评分从低到高排序
     */
    public List<HotelInfoVo> getSceneNearbyHotelWithLowestRatingRecommendation(List<HotelInfoVo> nearestHotel);

    /**
     * 按人均价格从高到低排序，没有价格的酒店排在最后，按远近排序
     * @param nearestHotel 最近的酒店列表 由getNearestHotel通过景点列表查出
     * @return 按人均价格从高到低
     */
    public List<HotelInfoVo> getSceneNearbyHotelWithHighestPriceRecommendation(List<HotelInfoVo> nearestHotel);

    /**
     * 按人均价格从低到高排序，没有价格的酒店排在最后，按远近排序
     * @param nearestHotel 最近的酒店列表 由getNearestHotel通过景点列表查出
     * @return 按人均价格从低到高
     */
    public List<HotelInfoVo> getSceneNearbyHotelWithLowestPriceRecommendation(List<HotelInfoVo> nearestHotel);

    /**
     * 通过传来的scene的uuid来绑定对应属性并封装进对象中返回
     * @param scenes 景点列表
     * @return 完整属性的景点列表
     */
    public List<Scene> bindSceneByUUID(List<Scene> scenes);

    /**
     * CF分页Api助手
     * @param sceneList 需要被分页的风景列表
     * @param pageNum 当前页码
     * @param pageSize 页码显示条数
     * @return 封装好的分页完成的map
     */
    public Map<String, Object> CFPage(List<Scene> sceneList, Integer pageNum, Integer pageSize);

    /**
     * 转换数据库中的用户画像返回用户喜欢的type列表
     * @param jsonObject 用户画像json对象
     * @return 返回用户画像中用户喜欢的列表
     */
    public List<String> getCustomerPortraitTypeList(JSONObject jsonObject);

    /**
     * 封装循环查找是否在布隆过滤器中存在，不存在就添加
     * @param res 要传递引用地址添加新的推荐景点的列表
     * @param list 被布隆过滤器检查是否已经推荐的列表
     * @param customer 当前用户 需含有id主键
     */
    public void loopCheckBloomFilter(List<Scene> list, List<Scene> res, Customer customer);

    public List<Scene> getEnoughGuessByCustomerPortraitAndRating(List<String> typeList, List<Long> recommendItems, Customer customer);
}
