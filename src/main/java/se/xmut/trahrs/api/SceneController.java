package se.xmut.trahrs.api;

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

        QueryWrapper queryWrapper = new QueryWrapper();
        for(Scene scene:scenes){
            queryWrapper.clear();
            queryWrapper.eq("scene_id", scene.getSceneId());
            scene.setLocation(sceneService.getOne(queryWrapper).getLocation());
        }

        List<HotelInfoVo> hotelInfoVoList = sceneService
                .getSceneNearbyHotelWithComprehensiveRecommendation(sceneService
                        .getNearestHotel(scenes, radius));

        List<HotelInfoVo> hotelInfoPage =  hotelInfoVoList.subList((pageNum-1)*pageSize, (pageNum-1)*pageSize+pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("records", hotelInfoPage);
        map.put("total", hotelInfoVoList.size());
        map.put("size", pageSize);
        map.put("current", pageNum);
        map.put("pages", hotelInfoVoList.size()%pageSize==0 ? hotelInfoVoList.size()/pageSize : hotelInfoVoList.size()/pageSize +1);
        return ApiResponse.ok(map);
    }

//    @WebLog(description = "根据分类查询的综合推荐景点分页")
//    @GetMapping("/ratingPage")
//    public ApiResponse findClassifyPage(){
//
//    }

}

