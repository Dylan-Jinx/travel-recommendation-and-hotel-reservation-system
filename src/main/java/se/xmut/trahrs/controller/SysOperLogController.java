package se.xmut.trahrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.service.SysOperLogService;
import se.xmut.trahrs.entity.SysOperLog;


/**
 * <p>
 * 操作日志记录 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/sysOperLog")
public class SysOperLogController {

    final Logger logger = LoggerFactory.getLogger(SysOperLogController.class);
    @Autowired
    private SysOperLogService sysOperLogService;

    @PostMapping
    public ApiResponse save(@RequestBody SysOperLog sysOperLog) {
        return ApiResponse.ok(sysOperLogService.saveOrUpdate(sysOperLog));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(sysOperLogService.removeById(id));
    }

    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(sysOperLogService.list());
    }

    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(sysOperLogService.list());
    }

    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(sysOperLogService.page(new Page<>(pageNum, pageSize)));
    }

}

