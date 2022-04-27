package se.xmut.trahrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.service.SysDictTypeService;
import se.xmut.trahrs.entity.SysDictType;


/**
 * <p>
 * 字典类型表 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/sysDictType")
public class SysDictTypeController {

    final Logger logger = LoggerFactory.getLogger(SysDictTypeController.class);
    @Autowired
    private SysDictTypeService sysDictTypeService;

    @PostMapping
    public ApiResponse save(@RequestBody SysDictType sysDictType) {
        return ApiResponse.ok(sysDictTypeService.saveOrUpdate(sysDictType));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(sysDictTypeService.removeById(id));
    }

    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(sysDictTypeService.list());
    }

    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(sysDictTypeService.list());
    }

    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(sysDictTypeService.page(new Page<>(pageNum, pageSize)));
    }

}

