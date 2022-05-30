package se.xmut.trahrs.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.PageUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.mahout.cf.taste.common.TasteException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.*;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.domain.dto.CustomerDto;
import se.xmut.trahrs.domain.model.Customer;
import se.xmut.trahrs.domain.model.HotelInfo;
import se.xmut.trahrs.domain.model.SceneComment;
import se.xmut.trahrs.domain.vo.HotelInfoVo;
import se.xmut.trahrs.exception.CFException;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.mapper.HotelInfoMapper;
import se.xmut.trahrs.mapper.SceneMapper;
import se.xmut.trahrs.service.HotelInfoService;
import se.xmut.trahrs.service.ItemBasedCfService;
import se.xmut.trahrs.service.SceneCommentService;
import se.xmut.trahrs.service.SceneService;
import se.xmut.trahrs.domain.model.Scene;
import se.xmut.trahrs.service.impl.BloomFilterRedisServiceImpl;
import se.xmut.trahrs.util.BloomFilterUtils;
import se.xmut.trahrs.util.CFUtils;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-20
 */
@RestController
@RequestMapping("/scene")
public class SceneController {

    final Logger logger = LoggerFactory.getLogger(SceneController.class);
    @Autowired
    private SceneService sceneService;
    @Autowired
    private HotelInfoService hotelInfoService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HotelInfoMapper hotelInfoMapper;

    @Autowired
    private SceneMapper sceneMapper;

    @Autowired
    private ItemBasedCfService itemBasedCfService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private SceneCommentService sceneCommentService;


    @WebLog(description = "添加")
    @PostMapping
    public ApiResponse save(@RequestBody Scene scene) {
        return ApiResponse.ok(sceneService.saveOrUpdate(scene));
    }

    @WebLog(description = "用id删除")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(sceneService.removeById(id));
    }

    @WebLog(description = "查询全部")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(sceneService.list());
    }

    @WebLog(description = "用id查找")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(sceneService.getById(id));
    }

    @WebLog(description = "分页")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(sceneService.page(new Page<>(pageNum, pageSize)));
    }

    @WebLog(description = "查询评分高的所有景点分页")
    @GetMapping("/ratingPage")
    public ApiResponse findRatingPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        QueryWrapper<Scene> sceneQueryWrapper = new QueryWrapper<>();
        sceneQueryWrapper.isNotNull("rating");
        sceneQueryWrapper.orderByDesc("rating");
        return ApiResponse.ok(sceneService.getBaseMapper().selectPage(new Page<>(pageNum, pageSize), sceneQueryWrapper));
    }

    @WebLog(description = "通过景点查询附近综合推荐酒店分页")
    @PostMapping("/NearbyComprehensiveRecommendationPage")
    public ApiResponse findNearbyComprehensiveRecommendationPage(@RequestParam Integer pageNum,
                                                                 @RequestParam Integer pageSize,
                                                                 @RequestBody List<Scene> scenes,
                                                                 @RequestParam Double radius){

        if (radius==null){
            radius = 1000.0;
        }

        sceneService.bindSceneByUUID(scenes);

        List<HotelInfoVo> hotelInfoVoList = sceneService
                .getSceneNearbyHotelWithComprehensiveRecommendation(sceneService
                        .getNearestHotel(scenes, radius));

        Map<String, Object> map = hotelInfoService.HotelInfoVoPage(hotelInfoVoList, pageNum, pageSize);

        return ApiResponse.ok(map);
    }

    @WebLog(description = "通过景点查询附近评分最高酒店分页")
    @PostMapping("/NearbyHighestRatingRecommendationPage")
    public ApiResponse findNearbyHighestRatingRecommendationPage(@RequestParam Integer pageNum,
                                                                 @RequestParam Integer pageSize,
                                                                 @RequestBody List<Scene> scenes,
                                                                 @RequestParam Double radius){
        if(radius==null){
            radius = 1000.0;
        }

        sceneService.bindSceneByUUID(scenes);

        List<HotelInfoVo> hotelInfoVoList = sceneService
                .getSceneNearbyHotelWithHighestRatingRecommendation(sceneService
                        .getNearestHotel(scenes, radius));

        Map<String, Object> map = hotelInfoService.HotelInfoVoPage(hotelInfoVoList, pageNum, pageSize);

        return ApiResponse.ok(map);
    }

    @WebLog(description = "通过景点查询附近评分最低酒店分页")
    @PostMapping("/NearbyLowestRatingRecommendationPage")
    public ApiResponse findNearbyLowestRatingRecommendationPage(@RequestParam Integer pageNum,
                                                                 @RequestParam Integer pageSize,
                                                                 @RequestBody List<Scene> scenes,
                                                                 @RequestParam Double radius){
        if(radius==null){
            radius = 1000.0;
        }

        sceneService.bindSceneByUUID(scenes);

        List<HotelInfoVo> hotelInfoVoList = sceneService
                .getSceneNearbyHotelWithLowestRatingRecommendation(sceneService
                        .getNearestHotel(scenes, radius));

        Map<String, Object> map = hotelInfoService.HotelInfoVoPage(hotelInfoVoList, pageNum, pageSize);

        return ApiResponse.ok(map);
    }

    /**FIXME 酒店价格暂未导入*/
    @WebLog(description = "通过景点查询附近价格最高酒店分页")
    @PostMapping("/NearbyHighestPriceRecommendationPage")
    public ApiResponse findNearbyHighestPriceRecommendationPage(@RequestParam Integer pageNum,
                                                                @RequestParam Integer pageSize,
                                                                @RequestBody List<Scene> scenes,
                                                                @RequestParam Double radius){
        if(radius==null){
            radius = 1000.0;
        }

        sceneService.bindSceneByUUID(scenes);

        List<HotelInfoVo> hotelInfoVoList = sceneService
                .getSceneNearbyHotelWithHighestPriceRecommendation(sceneService
                        .getNearestHotel(scenes, radius));

        Map<String, Object> map = hotelInfoService.HotelInfoVoPage(hotelInfoVoList, pageNum, pageSize);

        return ApiResponse.ok(map);
    }

    /**FIXME 酒店价格暂未导入*/
    @WebLog(description = "通过景点查询附近价格最低酒店分页")
    @PostMapping("/NearbyLowestPriceRecommendationPage")
    public ApiResponse findNearbyLowestPriceRecommendationPage(@RequestParam Integer pageNum,
                                                               @RequestParam Integer pageSize,
                                                               @RequestBody List<Scene> scenes,
                                                               @RequestParam Double radius){
        if(radius==null){
            radius = 1000.0;
        }

        sceneService.bindSceneByUUID(scenes);

        List<HotelInfoVo> hotelInfoVoList = sceneService
                .getSceneNearbyHotelWithLowestPriceRecommendation(sceneService
                        .getNearestHotel(scenes, radius));

        Map<String, Object> map = hotelInfoService.HotelInfoVoPage(hotelInfoVoList, pageNum, pageSize);

        return ApiResponse.ok(map);
    }

    /**FIXME 酒店价格暂未导入*/
    @WebLog(description = "使用价格区间通过附近景点查询附近酒店分页")
    @PostMapping("/NearbyPriceRangeRecommendationPage")
    public ApiResponse findNearbyPriceRangeRecommendationPage(@RequestParam Integer pageNum,
                                                              @RequestParam Integer pageSize,
                                                              @RequestBody List<Scene> scenes,
                                                              @RequestParam Double radius,
                                                              @RequestParam Integer priceBottom,
                                                              @RequestParam Integer priceTop){
        if(radius==null){
            radius = 1000.0;
        }

        sceneService.bindSceneByUUID(scenes);

        List<HotelInfoVo> hotelInfoVoList = sceneService.getNearestHotel(scenes, radius);
        List<String> nameList = new ArrayList<>();
        hotelInfoVoList.forEach(h->nameList.add(h.getName()));
        QueryWrapper<HotelInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("name", nameList);
        queryWrapper.between("cost", priceBottom, priceTop);

        return ApiResponse.ok(hotelInfoService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @WebLog(description = "协同过滤推荐")
    @PostMapping("/getItemBasedCFRecommendation")
    public ApiResponse getItemBasedCFRecommendation(@RequestBody Customer customer,
                                    @RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize) throws IOException, TasteException {

        List<Long> recommendItems = new ArrayList<>();
        boolean cf = false;

        if(customer!=null){
            cf = itemBasedCfService.isCanCf(customer.getCustomerId());
        }

        if(cf){
            recommendItems = itemBasedCfService.getItemBasedCFRecommendation((long) customer.getId(), null);
        }

        try{

            if(cf && !recommendItems.isEmpty()){

                Map<String, Object> map = new HashMap<>();
                int page = PageUtil.getStart(pageNum-1, pageSize);
                map.put("recommendItems", recommendItems);
                map.put("pageStart", page);
                map.put("pageSize", pageSize);
                List<Scene> sceneIPage = sceneMapper.getPageByPK(map);

                return ApiResponse.ok(sceneService.myPage(sceneIPage, pageNum, pageSize, null));
            }else {

                List<String> typeList = sceneService.getCustomerPortraitTypeList(
                        JSONUtil.parseObj(customer.getCustomerPortrait()));

                int page = PageUtil.getStart(pageNum-1, pageSize);
                Map<String, Object> map = new HashMap<>();
                map.put("pageStart", page);
                map.put("pageSize", pageSize);
                map.put("typeList", typeList);

                List<Scene> sceneIPage = sceneMapper.getPageByType(map);

                return ApiResponse.ok(sceneService.myPage(sceneIPage, pageNum, pageSize, null));
            }

            //如果无用户画像或csv文件出错按rating推荐
        }catch (Exception e){
            System.err.println(new CFException("推荐失败，请检查csv文件或用户画像"));
            QueryWrapper<Scene> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByDesc("rating");

            return ApiResponse.ok(sceneService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }

    }

    @WebLog(description = "猜你喜欢")
    @PostMapping("/guessYouLike")
    public ApiResponse guessYouLike(@RequestBody Customer customer) throws IOException, TasteException {
        List<Long> recommendItems = new ArrayList<>();
        boolean cf = itemBasedCfService.isCanCf(customer.getCustomerId());

        if(cf){
            recommendItems = itemBasedCfService.guessYouLike((long) customer.getId(), null, null);
        }

        QueryWrapper<Scene> queryWrapper = new QueryWrapper<>();

        try{

            //cf有结果
            if(cf && !recommendItems.isEmpty()){

                //如果推荐数不足三个，去按用户画像再查到满足三个出来
                if(recommendItems.size()<3){
                    List<String> typeList = sceneService.getCustomerPortraitTypeList(
                            JSONUtil.parseObj(customer.getCustomerPortrait()));

                    List<Scene> res = sceneService.getEnoughGuessByCustomerPortraitAndRating(typeList, recommendItems, customer);

                    return ApiResponse.ok(res);

                    //cf的直接够用
                }else {
                    queryWrapper.in("id", recommendItems);
                    queryWrapper.orderByDesc("rating");

                    return ApiResponse.ok(sceneMapper.selectList(queryWrapper));
                }

            //cf无法推荐，直接用用户画像
            }else {
                List<String> typeList = sceneService.getCustomerPortraitTypeList(
                        JSONUtil.parseObj(customer.getCustomerPortrait()));

                List<Scene> res = sceneService.getEnoughGuessByCustomerPortraitAndRating(typeList, null, customer);

                return ApiResponse.ok(res);
            }

        }catch (Exception e){
            System.err.println(new CFException("推荐失败，请检查csv文件或用户画像，也有可能是这个用户七天内推荐系统为他推荐的已使用完，查看redis"));
            queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByDesc("rating");
            int pageNum = 1, pageSize = 50;
            List<Scene> ratingScenes = sceneService.page(new Page<>(pageNum, pageSize), queryWrapper).getRecords();
            List<Scene> res = new ArrayList<>();

            while(res.size()<3) {
                sceneService.loopCheckBloomFilter(ratingScenes, res, customer);
            }

            return ApiResponse.ok(res);
        }
    }

    @WebLog(description = "情感分析值最高评论")
    @PostMapping("/BestSemantic")
    public ApiResponse getBestSemanticResult(@RequestBody Scene scene){
        QueryWrapper<SceneComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("scene_id", scene.getSceneId());
        queryWrapper.orderByDesc("semantic");
        queryWrapper.last("limit 1");
        return ApiResponse.ok(sceneCommentService.getOne(queryWrapper));
    }

    @WebLog(description = "根据分类查询的综合推荐景点分页")
    @GetMapping("/classifyPage")
    public ApiResponse findClassifyPage(@RequestParam List<String> typeList,
                                        @RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize){

        int page = PageUtil.getStart(pageNum-1, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("pageStart", page);
        map.put("pageSize", pageSize);
        map.put("typeList", typeList);

        List<Scene> scenes = sceneMapper.getPageByType(map);
        Integer cnt = sceneMapper.countType(typeList);

        return ApiResponse.ok(sceneService.myPage(scenes, pageNum, pageSize, (long)cnt));
    }

    @WebLog(description = "查询最高评分")
    @GetMapping("/HighestRating")
    public ApiResponse getBestRatingScene(@RequestParam Integer num){
        QueryWrapper<Scene> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("rating").last("limit "+num);

        return ApiResponse.ok(sceneMapper.selectList(queryWrapper));
    }

    @WebLog(description = "模糊查询景点名称")
    @GetMapping("/likeName")
    public ApiResponse findSceneByName(@RequestParam String sceneName,
                                       @RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize){
        QueryWrapper<Scene> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", sceneName);

        return ApiResponse.ok(sceneService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @WebLog(description = "修改景点信息")
    @PutMapping("/updateScene")
    public ApiResponse updateScene(@RequestBody Scene scene){
        return ApiResponse.ok(sceneService.updateById(scene));
    }

    @WebLog(description = "最多评价，评价相同按评分")
    @GetMapping("/mostCommentElseRating")
    public ApiResponse mostCommentElseRating(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        int page = PageUtil.getStart(pageNum-1, pageSize);

        return ApiResponse.ok(sceneService.myPage(sceneMapper.getMostCommentElseRating(page, pageSize), pageNum, pageSize, null));
    }
}

