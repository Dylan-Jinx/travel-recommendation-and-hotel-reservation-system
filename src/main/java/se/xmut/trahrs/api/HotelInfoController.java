package se.xmut.trahrs.api;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.domain.dto.HotelInfoDto;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.HotelInfoService;
import se.xmut.trahrs.domain.model.HotelInfo;


/**
 * <p>
 * 酒店信息 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/hotelInfo")
public class HotelInfoController {

    final Logger logger = LoggerFactory.getLogger(HotelInfoController.class);
    @Autowired
    private HotelInfoService hotelInfoService;
    @Autowired
    private ModelMapper modelMapper;

    @WebLog(description = "添加酒店信息")
    @PostMapping
    public ApiResponse save(@RequestBody HotelInfoDto hotelInfoDto) {
        HotelInfo hotelInfo = modelMapper.map(hotelInfoDto,HotelInfo.class);
        hotelInfo.setHotelId(IdUtil.objectId());
        hotelInfo.setCreateTime(LocalDateTimeUtil.now());
        hotelInfo.setViewCount(0);
        return ApiResponse.ok("操作成功",hotelInfoService.save(hotelInfo));
    }

    @WebLog(description = "用id删除酒店信息")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(hotelInfoService.removeById(id));
    }

    @WebLog(description = "查询全部酒店信息")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(hotelInfoService.list());
    }

    @WebLog(description = "用id查找酒店信息")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(hotelInfoService.getById(id));
    }

    @WebLog(description = "分页酒店信息")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(hotelInfoService.page(new Page<>(pageNum, pageSize)));
    }

    @WebLog(description = "修改酒店信息")
    @PutMapping
    public ApiResponse update(@RequestBody HotelInfo hotelInfo){
        return ApiResponse.ok("操作成功",hotelInfoService.updateById(hotelInfo));
    }

    @WebLog(description = "查询酒店相关信息")
    @PostMapping("multiConditionQuery")
    public ApiResponse multiConditionQuery(@RequestParam Integer pageIndex,
                                           @RequestParam Integer pageSize,
                                           @RequestBody HotelInfo hotelInfo){
        Page<HotelInfo> hotelInfoPage = new Page<>(pageIndex,pageSize);
        QueryWrapper<HotelInfo> hotelInfoQueryWrapper = new QueryWrapper<>();
        hotelInfoQueryWrapper.like("hotel_name",hotelInfo.getHotelName())
                .like("area",hotelInfo.getArea());
        return ApiResponse.ok("获取成功",hotelInfoService.page(hotelInfoPage,hotelInfoQueryWrapper));
    }

    @WebLog(description = "点击次数")
    @GetMapping("/viewCount")
    public ApiResponse viewCount(@RequestParam String hotelId){
        QueryWrapper<HotelInfo> hotelInfoQueryWrapper = new QueryWrapper<>();
        hotelInfoQueryWrapper.eq("hotel_id",hotelId).last("limit 1");

        HotelInfo hotelInfo = hotelInfoService.getOne(hotelInfoQueryWrapper);
        Integer viewCount = hotelInfo.getViewCount();
        viewCount = viewCount + 1;
        hotelInfo.setViewCount(viewCount);

        return ApiResponse.ok("操作成功",hotelInfoService.updateById(hotelInfo));
    }
}

