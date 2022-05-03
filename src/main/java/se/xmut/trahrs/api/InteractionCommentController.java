package se.xmut.trahrs.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.InteractionCommentService;
import se.xmut.trahrs.domain.model.InteractionComment;


/**
 * <p>
 * 交流评论 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/interactionComment")
public class InteractionCommentController {

    final Logger logger = LoggerFactory.getLogger(InteractionCommentController.class);
    @Autowired
    private InteractionCommentService interactionCommentService;

    @WebLog(description = "添加交流评论")
    @PostMapping
    public ApiResponse save(@RequestBody InteractionComment interactionComment) {
        return ApiResponse.ok(interactionCommentService.saveOrUpdate(interactionComment));
    }

    @WebLog(description = "用id删除交流评论")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(interactionCommentService.removeById(id));
    }

    @WebLog(description = "查询全部交流评论")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(interactionCommentService.list());
    }

    @WebLog(description = "用id查找交流评论")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(interactionCommentService.getById(id));
    }

    @WebLog(description = "分页交流评论")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(interactionCommentService.page(new Page<>(pageNum, pageSize)));
    }

}

