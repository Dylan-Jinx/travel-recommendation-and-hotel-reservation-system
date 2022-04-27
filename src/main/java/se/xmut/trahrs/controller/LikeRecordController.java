package se.xmut.trahrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.service.LikeRecordService;
import se.xmut.trahrs.entity.LikeRecord;


/**
 * <p>
 * 点赞记录 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/likeRecord")
public class LikeRecordController {

    final Logger logger = LoggerFactory.getLogger(LikeRecordController.class);
    @Autowired
    private LikeRecordService likeRecordService;

    @PostMapping
    public ApiResponse save(@RequestBody LikeRecord likeRecord) {
        return ApiResponse.ok(likeRecordService.saveOrUpdate(likeRecord));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(likeRecordService.removeById(id));
    }

    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(likeRecordService.list());
    }

    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(likeRecordService.list());
    }

    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(likeRecordService.page(new Page<>(pageNum, pageSize)));
    }

}

