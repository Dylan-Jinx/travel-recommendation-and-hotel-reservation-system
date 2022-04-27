package se.xmut.trahrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.service.HotelInfoService;
import se.xmut.trahrs.entity.HotelInfo;


/**
 * <p>
 * 酒店信息 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/hotelInfo")
public class HotelInfoController {

    final Logger logger = LoggerFactory.getLogger(HotelInfoController.class);
    @Autowired
    private HotelInfoService hotelInfoService;

    @PostMapping
    public ApiResponse save(@RequestBody HotelInfo hotelInfo) {
        return ApiResponse.ok(hotelInfoService.saveOrUpdate(hotelInfo));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(hotelInfoService.removeById(id));
    }

    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(hotelInfoService.list());
    }

    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(hotelInfoService.list());
    }

    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(hotelInfoService.page(new Page<>(pageNum, pageSize)));
    }

}

