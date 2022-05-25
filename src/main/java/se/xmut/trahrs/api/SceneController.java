package se.xmut.trahrs.api;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.domain.model.HotelInfo;
import se.xmut.trahrs.domain.vo.HotelInfoVo;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.mapper.HotelInfoMapper;
import se.xmut.trahrs.service.HotelInfoService;
import se.xmut.trahrs.service.SceneService;
import se.xmut.trahrs.domain.model.Scene;


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
    @GetMapping("/NearbyComprehensiveRecommendationPage")
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
    @GetMapping("/NearbyHighestRatingRecommendationPage")
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
    @GetMapping("/NearbyLowestRatingRecommendationPage")
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
    @GetMapping("/NearbyHighestPriceRecommendationPage")
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
    @GetMapping("/NearbyLowestPriceRecommendationPage")
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
    @GetMapping("/NearbyPriceRangeRecommendationPage")
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

//    @WebLog(description = "根据分类查询的综合推荐景点分页")
//    @GetMapping("/ratingPage")
//    public ApiResponse findClassifyPage(){
//
//    }

}

