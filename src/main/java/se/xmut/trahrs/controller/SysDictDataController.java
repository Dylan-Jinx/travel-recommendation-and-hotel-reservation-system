package se.xmut.trahrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.service.SysDictDataService;
import se.xmut.trahrs.entity.SysDictData;


/**
 * <p>
 * 字典数据表 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/sysDictData")
public class SysDictDataController {

    final Logger logger = LoggerFactory.getLogger(SysDictDataController.class);
    @Autowired
    private SysDictDataService sysDictDataService;

    @PostMapping
    public ApiResponse save(@RequestBody SysDictData sysDictData) {
        return ApiResponse.ok(sysDictDataService.saveOrUpdate(sysDictData));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(sysDictDataService.removeById(id));
    }

    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(sysDictDataService.list());
    }

    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(sysDictDataService.list());
    }

    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(sysDictDataService.page(new Page<>(pageNum, pageSize)));
    }

}

