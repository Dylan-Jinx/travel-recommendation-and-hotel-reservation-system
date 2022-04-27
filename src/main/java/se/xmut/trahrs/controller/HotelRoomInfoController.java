package se.xmut.trahrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.service.HotelRoomInfoService;
import se.xmut.trahrs.entity.HotelRoomInfo;


/**
 * <p>
 * 酒店房间信息 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/hotelRoomInfo")
public class HotelRoomInfoController {

    final Logger logger = LoggerFactory.getLogger(HotelRoomInfoController.class);
    @Autowired
    private HotelRoomInfoService hotelRoomInfoService;

    @PostMapping
    public ApiResponse save(@RequestBody HotelRoomInfo hotelRoomInfo) {
        return ApiResponse.ok(hotelRoomInfoService.saveOrUpdate(hotelRoomInfo));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(hotelRoomInfoService.removeById(id));
    }

    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(hotelRoomInfoService.list());
    }

    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(hotelRoomInfoService.list());
    }

    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(hotelRoomInfoService.page(new Page<>(pageNum, pageSize)));
    }

}

