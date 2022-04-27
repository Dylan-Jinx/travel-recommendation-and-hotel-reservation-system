package se.xmut.trahrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.service.RestaurantService;
import se.xmut.trahrs.entity.Restaurant;


/**
 * <p>
 * 餐馆信息 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    final Logger logger = LoggerFactory.getLogger(RestaurantController.class);
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ApiResponse save(@RequestBody Restaurant restaurant) {
        return ApiResponse.ok(restaurantService.saveOrUpdate(restaurant));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(restaurantService.removeById(id));
    }

    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(restaurantService.list());
    }

    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(restaurantService.list());
    }

    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(restaurantService.page(new Page<>(pageNum, pageSize)));
    }

}

