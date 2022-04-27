package se.xmut.trahrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.service.CarouselService;
import se.xmut.trahrs.entity.Carousel;


/**
 * <p>
 * 公告轮播图 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/carousel")
public class CarouselController {

    final Logger logger = LoggerFactory.getLogger(CarouselController.class);
    @Autowired
    private CarouselService carouselService;

    @PostMapping
    public ApiResponse save(@RequestBody Carousel carousel) {
        return ApiResponse.ok(carouselService.saveOrUpdate(carousel));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(carouselService.removeById(id));
    }

    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(carouselService.list());
    }

    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(carouselService.list());
    }

    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(carouselService.page(new Page<>(pageNum, pageSize)));
    }

}

