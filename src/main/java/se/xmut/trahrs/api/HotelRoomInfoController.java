package se.xmut.trahrs.api;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.domain.dto.HotelRoomInfoDto;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.HotelRoomInfoService;
import se.xmut.trahrs.domain.model.HotelRoomInfo;


/**
 * <p>
 * 酒店房间信息 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/hotelRoomInfo")
public class HotelRoomInfoController {

    final Logger logger = LoggerFactory.getLogger(HotelRoomInfoController.class);
    @Autowired
    private HotelRoomInfoService hotelRoomInfoService;

    @Autowired
    private ModelMapper modelMapper;

    @WebLog(description = "添加酒店房间信息")
    @PostMapping("/addRoomInfo")
    public ApiResponse save(@RequestBody HotelRoomInfoDto hotelRoomInfoDto) {
        HotelRoomInfo hotelRoomInfo= modelMapper.map(hotelRoomInfoDto,HotelRoomInfo.class);

        Integer remainCount=hotelRoomInfo.getRemainCount();
        Integer roomTypeId=hotelRoomInfo.getRoomTypeId();

        UpdateWrapper<HotelRoomInfo> hotelRoomInfoUpdateWrapper=new UpdateWrapper<>();
        hotelRoomInfoUpdateWrapper
                .eq("room_type_id",roomTypeId);

        HotelRoomInfo updateHotelRoomInfo=new HotelRoomInfo();


        if(remainCount <= 0){
            return ApiResponse.ok("添加失败，没有剩余的房间数");
        }

        hotelRoomInfo.setRoomId(IdUtil.objectId());
        hotelRoomInfoService.save(hotelRoomInfo);
        updateHotelRoomInfo.setRemainCount(remainCount-1);
        hotelRoomInfoService.update(updateHotelRoomInfo,hotelRoomInfoUpdateWrapper);

        return ApiResponse.ok("房间信息添加成功");
    }

    @WebLog(description = "用id删除酒店房间信息")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(hotelRoomInfoService.removeById(id));
    }

    @WebLog(description = "查询全部酒店房间信息")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(hotelRoomInfoService.list());
    }

    @WebLog(description = "用id查找酒店房间信息")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(hotelRoomInfoService.getById(id));
    }

    @WebLog(description = "分页酒店房间信息")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(hotelRoomInfoService.page(new Page<>(pageNum, pageSize)));
    }
    @WebLog(description = "根据房间类型查找")
    @GetMapping("/findByRoomTypeId")
    public ApiResponse findByRoomTypeId(@RequestParam Integer roomTypeId) {
        QueryWrapper<HotelRoomInfo> hotelRoomInfoQueryWrapper=new QueryWrapper<>();
        hotelRoomInfoQueryWrapper
                .eq("room_type_id",roomTypeId);

        List<HotelRoomInfo> hotelRoomInfoList = hotelRoomInfoService.list(hotelRoomInfoQueryWrapper);

        return ApiResponse.ok("查找成功",hotelRoomInfoList);
    }



}

