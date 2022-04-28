package se.xmut.trahrs.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.RestaurantDishesService;
import se.xmut.trahrs.domain.model.RestaurantDishes;


/**
 * <p>
 * 餐馆菜品 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
@RestController
@RequestMapping("/restaurantDishes")
public class RestaurantDishesController {

    final Logger logger = LoggerFactory.getLogger(RestaurantDishesController.class);
    @Autowired
    private RestaurantDishesService restaurantDishesService;

    @WebLog(description = "添加餐馆菜品")
    @PostMapping
    public ApiResponse save(@RequestBody RestaurantDishes restaurantDishes) {
        return ApiResponse.ok(restaurantDishesService.saveOrUpdate(restaurantDishes));
    }

    @WebLog(description = "用id删除餐馆菜品")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(restaurantDishesService.removeById(id));
    }

    @WebLog(description = "查询全部餐馆菜品")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(restaurantDishesService.list());
    }

    @WebLog(description = "用id查找餐馆菜品")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(restaurantDishesService.list());
    }

    @WebLog(description = "分页餐馆菜品")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(restaurantDishesService.page(new Page<>(pageNum, pageSize)));
    }

}

