package se.xmut.trahrs.api;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.domain.dto.HotelInfoDto;
import org.springframework.web.bind.annotation.*;
import se.xmut.trahrs.domain.model.HotelInfo;



import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.manager.RedisService;
import se.xmut.trahrs.manager.cache.annoation.Cacheable;
import se.xmut.trahrs.mapper.HotelInfoMapper;
import se.xmut.trahrs.service.HotelInfoService;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-20
 */
@RestController
@RequestMapping("/hotelInfo")
public class HotelInfoController {

    final Logger logger = LoggerFactory.getLogger(HotelInfoController.class);
    @Autowired
    private HotelInfoService hotelInfoService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private HotelInfoMapper hotelInfoMapper;

    @Autowired
    private ModelMapper modelMapper;

    @WebLog(description = "添加")
    @PostMapping("/save")
    public ApiResponse save(@RequestBody HotelInfoDto hotelInfoDto) {

        HotelInfo hotelInfo = modelMapper.map(hotelInfoDto, HotelInfo.class);

        String hotelName = hotelInfo.getName();
        String tel = hotelInfo.getTel();

        QueryWrapper<HotelInfo> hotelInfoQueryWrapper = new QueryWrapper<>();
        hotelInfoQueryWrapper.eq("name", hotelName).eq("tel", tel);
        //根据酒店名和电话查找酒店
        if (!ObjectUtil.isNull(hotelInfoService.getOne(hotelInfoQueryWrapper))) {
            return ApiResponse.error("已有酒店");
        }

        hotelInfo.setHotelId(IdUtil.objectId());
        hotelInfoService.save(hotelInfo);

        return ApiResponse.ok("酒店信息添加成功！");
    }

    @WebLog(description = "用id删除")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(hotelInfoService.removeById(id));
    }

    @WebLog(description = "查询全部")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(hotelInfoService.list());
    }

    @WebLog(description = "用id查找")
    @Cacheable(cacheName = "hotelinfo", key = "#id", capacity = 3, log = true)
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(hotelInfoService.getById(id));
    }

    @WebLog(description = "分页")
    @GetMapping("/page")

    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(hotelInfoService.page(new Page<>(pageNum, pageSize)));
    }

    @WebLog(description = "酒店模糊查询")
    @GetMapping("/findLike")
    public ApiResponse Hotel_like(@RequestParam String likeName,
        @RequestParam Integer pageNum, @RequestParam Integer pageSize) {

        QueryWrapper<HotelInfo> hotelInfoQueryWrapper = new QueryWrapper<>();
        hotelInfoQueryWrapper.like("name", likeName);

        List<HotelInfo> hotelInfoList = hotelInfoService.list(hotelInfoQueryWrapper);

        if(hotelInfoList.isEmpty()){
            return ApiResponse.error("沒有这个酒店信息");
        }

        return ApiResponse.ok(hotelInfoService.page(new Page<>(pageNum, pageSize), hotelInfoQueryWrapper));
    }

    @WebLog(description = "酒店信息更新")
    @PutMapping("/update")
    public ApiResponse hotel_update(@RequestBody HotelInfoDto hotelInfoDto){
        HotelInfo hotelInfo=modelMapper.map(hotelInfoDto,HotelInfo.class);

        String hotel_id=hotelInfo.getHotelId();
        UpdateWrapper<HotelInfo> hotelInfoUpdateWrapper=new UpdateWrapper<>();
        hotelInfoUpdateWrapper
                .eq("hotel_id",hotel_id);

        hotelInfoService.update(hotelInfo,hotelInfoUpdateWrapper);

        return ApiResponse.ok("更新成功");

    }

    @WebLog(description = "酒店类型查找")
    @PostMapping("/finByType")
    public ApiResponse finByType(@RequestBody HotelInfoDto hotelInfoDto){
        HotelInfo hotelInfo=modelMapper.map(hotelInfoDto,HotelInfo.class);

        String star=hotelInfo.getType();
        String type=hotelInfo.getKeyTag();
        String name_brand=hotelInfo.getName();

        List<HotelInfo> hotelInfoList=hotelInfoService.findHotelByTypeAndKeyTagAndName(star,type,name_brand);
        if (hotelInfoList.isEmpty()){
            return ApiResponse.error("列表为空");
        }

        return ApiResponse.ok("查询成功",hotelInfoList);

    }

    @WebLog(description = "查询酒店的订单")
    @PostMapping("/findByOrderDetail")
    public ApiResponse findByOrderCount(@RequestBody HotelInfoDto hotelInfoDto){
        List<HotelInfo> hotelInfoList=hotelInfoMapper.findByOrderCount(hotelInfoDto.getHotelId());
        if (hotelInfoList.size()<=0){
            return ApiResponse.error("该酒店还没有订单");
        }
        return ApiResponse.ok("查询成功",hotelInfoList.get(0).getOrderDetailList());

    }












//    @WebLog(description = "查询酒店销量")
//    @PostMapping("/findByHotelCount")
//    public ApiResponse findByHotelCount(@RequestBody HotelInfoDto hotelInfoDto){
//        List<HotelInfo> hotelInfoList=hotelInfoMapper.findByOrderCount(hotelInfoDto.getHotelId());
//        if(hotelInfoList.size()<=0){
//            ApiResponse.error("该酒店还没有销量");
//        }
//        return ApiResponse.ok("查询成功",hotelInfoList.get(0).getOrderDetailList().size());
//
//    }
}

