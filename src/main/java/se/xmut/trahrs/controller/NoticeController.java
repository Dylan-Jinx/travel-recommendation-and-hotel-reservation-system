package se.xmut.trahrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.service.NoticeService;
import se.xmut.trahrs.entity.Notice;


/**
 * <p>
 * 公告 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    final Logger logger = LoggerFactory.getLogger(NoticeController.class);
    @Autowired
    private NoticeService noticeService;

    @PostMapping
    public ApiResponse save(@RequestBody Notice notice) {
        return ApiResponse.ok(noticeService.saveOrUpdate(notice));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(noticeService.removeById(id));
    }

    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(noticeService.list());
    }

    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(noticeService.list());
    }

    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(noticeService.page(new Page<>(pageNum, pageSize)));
    }

}

