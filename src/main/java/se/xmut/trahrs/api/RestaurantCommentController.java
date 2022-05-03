package se.xmut.trahrs.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.RestaurantCommentService;
import se.xmut.trahrs.domain.model.RestaurantComment;


/**
 * <p>
 * 餐馆评论 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/restaurantComment")
public class RestaurantCommentController {

    final Logger logger = LoggerFactory.getLogger(RestaurantCommentController.class);
    @Autowired
    private RestaurantCommentService restaurantCommentService;

    @WebLog(description = "添加餐馆评论")
    @PostMapping
    public ApiResponse save(@RequestBody RestaurantComment restaurantComment) {
        return ApiResponse.ok(restaurantCommentService.saveOrUpdate(restaurantComment));
    }

    @WebLog(description = "用id删除餐馆评论")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(restaurantCommentService.removeById(id));
    }

    @WebLog(description = "查询全部餐馆评论")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(restaurantCommentService.list());
    }

    @WebLog(description = "用id查找餐馆评论")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(restaurantCommentService.getById(id));
    }

    @WebLog(description = "分页餐馆评论")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(restaurantCommentService.page(new Page<>(pageNum, pageSize)));
    }

}

