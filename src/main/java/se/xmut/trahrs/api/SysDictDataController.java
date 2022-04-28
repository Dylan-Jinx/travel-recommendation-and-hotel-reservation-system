package se.xmut.trahrs.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.SysDictDataService;
import se.xmut.trahrs.domain.model.SysDictData;


/**
 * <p>
 * 字典数据表 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
@RestController
@RequestMapping("/sysDictData")
public class SysDictDataController {

    final Logger logger = LoggerFactory.getLogger(SysDictDataController.class);
    @Autowired
    private SysDictDataService sysDictDataService;

    @WebLog(description = "添加字典数据表")
    @PostMapping
    public ApiResponse save(@RequestBody SysDictData sysDictData) {
        return ApiResponse.ok(sysDictDataService.saveOrUpdate(sysDictData));
    }

    @WebLog(description = "用id删除字典数据表")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(sysDictDataService.removeById(id));
    }

    @WebLog(description = "查询全部字典数据表")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(sysDictDataService.list());
    }

    @WebLog(description = "用id查找字典数据表")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(sysDictDataService.list());
    }

    @WebLog(description = "分页字典数据表")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(sysDictDataService.page(new Page<>(pageNum, pageSize)));
    }

}

