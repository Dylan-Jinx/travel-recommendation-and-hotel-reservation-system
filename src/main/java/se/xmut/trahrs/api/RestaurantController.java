package se.xmut.trahrs.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.RestaurantService;
import se.xmut.trahrs.domain.model.Restaurant;


/**
 * <p>
 * 餐馆信息 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    final Logger logger = LoggerFactory.getLogger(RestaurantController.class);
    @Autowired
    private RestaurantService restaurantService;

    @WebLog(description = "添加餐馆信息")
    @PostMapping
    public ApiResponse save(@RequestBody Restaurant restaurant) {
        return ApiResponse.ok(restaurantService.saveOrUpdate(restaurant));
    }

    @WebLog(description = "用id删除餐馆信息")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(restaurantService.removeById(id));
    }

    @WebLog(description = "查询全部餐馆信息")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(restaurantService.list());
    }

    @WebLog(description = "用id查找餐馆信息")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(restaurantService.getById(id));
    }

    @WebLog(description = "分页餐馆信息")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(restaurantService.page(new Page<>(pageNum, pageSize)));
    }

}

