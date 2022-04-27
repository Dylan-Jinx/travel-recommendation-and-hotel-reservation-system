package se.xmut.trahrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.service.SceneCommentService;
import se.xmut.trahrs.entity.SceneComment;


/**
 * <p>
 * 景区评论 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/sceneComment")
public class SceneCommentController {

    final Logger logger = LoggerFactory.getLogger(SceneCommentController.class);
    @Autowired
    private SceneCommentService sceneCommentService;

    @PostMapping
    public ApiResponse save(@RequestBody SceneComment sceneComment) {
        return ApiResponse.ok(sceneCommentService.saveOrUpdate(sceneComment));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(sceneCommentService.removeById(id));
    }

    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(sceneCommentService.list());
    }

    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(sceneCommentService.list());
    }

    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(sceneCommentService.page(new Page<>(pageNum, pageSize)));
    }

}

