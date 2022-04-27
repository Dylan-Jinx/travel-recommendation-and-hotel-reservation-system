package se.xmut.trahrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.service.HotelCommentService;
import se.xmut.trahrs.entity.HotelComment;


/**
 * <p>
 * 酒店评论 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/hotelComment")
public class HotelCommentController {

    final Logger logger = LoggerFactory.getLogger(HotelCommentController.class);
    @Autowired
    private HotelCommentService hotelCommentService;

    @PostMapping
    public ApiResponse save(@RequestBody HotelComment hotelComment) {
        return ApiResponse.ok(hotelCommentService.saveOrUpdate(hotelComment));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(hotelCommentService.removeById(id));
    }

    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(hotelCommentService.list());
    }

    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(hotelCommentService.list());
    }

    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(hotelCommentService.page(new Page<>(pageNum, pageSize)));
    }

}

