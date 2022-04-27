package se.xmut.trahrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.service.SysJobService;
import se.xmut.trahrs.entity.SysJob;


/**
 * <p>
 * 定时任务调度表 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/sysJob")
public class SysJobController {

    final Logger logger = LoggerFactory.getLogger(SysJobController.class);
    @Autowired
    private SysJobService sysJobService;

    @PostMapping
    public ApiResponse save(@RequestBody SysJob sysJob) {
        return ApiResponse.ok(sysJobService.saveOrUpdate(sysJob));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(sysJobService.removeById(id));
    }

    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(sysJobService.list());
    }

    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(sysJobService.list());
    }

    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(sysJobService.page(new Page<>(pageNum, pageSize)));
    }

}

