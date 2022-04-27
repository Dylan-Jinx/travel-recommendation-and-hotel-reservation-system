package se.xmut.trahrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.service.RestaurantCommentService;
import se.xmut.trahrs.entity.RestaurantComment;


/**
 * <p>
 * 餐馆评论 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/restaurantComment")
public class RestaurantCommentController {

    final Logger logger = LoggerFactory.getLogger(RestaurantCommentController.class);
    @Autowired
    private RestaurantCommentService restaurantCommentService;

    @PostMapping
    public ApiResponse save(@RequestBody RestaurantComment restaurantComment) {
        return ApiResponse.ok(restaurantCommentService.saveOrUpdate(restaurantComment));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(restaurantCommentService.removeById(id));
    }

    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(restaurantCommentService.list());
    }

    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(restaurantCommentService.list());
    }

    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(restaurantCommentService.page(new Page<>(pageNum, pageSize)));
    }

}

