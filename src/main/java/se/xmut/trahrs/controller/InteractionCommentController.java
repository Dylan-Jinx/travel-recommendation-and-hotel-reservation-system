package se.xmut.trahrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.service.InteractionCommentService;
import se.xmut.trahrs.entity.InteractionComment;


/**
 * <p>
 * 交流评论 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/interactionComment")
public class InteractionCommentController {

    final Logger logger = LoggerFactory.getLogger(InteractionCommentController.class);
    @Autowired
    private InteractionCommentService interactionCommentService;

    @PostMapping
    public ApiResponse save(@RequestBody InteractionComment interactionComment) {
        return ApiResponse.ok(interactionCommentService.saveOrUpdate(interactionComment));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(interactionCommentService.removeById(id));
    }

    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(interactionCommentService.list());
    }

    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(interactionCommentService.list());
    }

    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(interactionCommentService.page(new Page<>(pageNum, pageSize)));
    }

}

