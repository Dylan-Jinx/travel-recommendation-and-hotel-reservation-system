package se.xmut.trahrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.service.RestaurantDishesService;
import se.xmut.trahrs.entity.RestaurantDishes;


/**
 * <p>
 * 餐馆菜品 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/restaurantDishes")
public class RestaurantDishesController {

    final Logger logger = LoggerFactory.getLogger(RestaurantDishesController.class);
    @Autowired
    private RestaurantDishesService restaurantDishesService;

    @PostMapping
    public ApiResponse save(@RequestBody RestaurantDishes restaurantDishes) {
        return ApiResponse.ok(restaurantDishesService.saveOrUpdate(restaurantDishes));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(restaurantDishesService.removeById(id));
    }

    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(restaurantDishesService.list());
    }

    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(restaurantDishesService.list());
    }

    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(restaurantDishesService.page(new Page<>(pageNum, pageSize)));
    }

}

