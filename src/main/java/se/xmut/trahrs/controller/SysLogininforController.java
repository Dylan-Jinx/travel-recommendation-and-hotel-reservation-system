package se.xmut.trahrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.service.SysLogininforService;
import se.xmut.trahrs.entity.SysLogininfor;


/**
 * <p>
 * 系统访问记录 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/sysLogininfor")
public class SysLogininforController {

    final Logger logger = LoggerFactory.getLogger(SysLogininforController.class);
    @Autowired
    private SysLogininforService sysLogininforService;

    @PostMapping
    public ApiResponse save(@RequestBody SysLogininfor sysLogininfor) {
        return ApiResponse.ok(sysLogininforService.saveOrUpdate(sysLogininfor));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(sysLogininforService.removeById(id));
    }

    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(sysLogininforService.list());
    }

    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(sysLogininforService.list());
    }

    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(sysLogininforService.page(new Page<>(pageNum, pageSize)));
    }

}

