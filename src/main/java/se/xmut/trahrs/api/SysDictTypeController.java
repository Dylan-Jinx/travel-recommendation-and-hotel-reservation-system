package se.xmut.trahrs.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.SysDictTypeService;
import se.xmut.trahrs.domain.model.SysDictType;


/**
 * <p>
 * 字典类型表 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
@RestController
@RequestMapping("/sysDictType")
public class SysDictTypeController {

    final Logger logger = LoggerFactory.getLogger(SysDictTypeController.class);
    @Autowired
    private SysDictTypeService sysDictTypeService;

    @WebLog(description = "添加字典类型表")
    @PostMapping
    public ApiResponse save(@RequestBody SysDictType sysDictType) {
        return ApiResponse.ok(sysDictTypeService.saveOrUpdate(sysDictType));
    }

    @WebLog(description = "用id删除字典类型表")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(sysDictTypeService.removeById(id));
    }

    @WebLog(description = "查询全部字典类型表")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(sysDictTypeService.list());
    }

    @WebLog(description = "用id查找字典类型表")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(sysDictTypeService.list());
    }

    @WebLog(description = "分页字典类型表")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(sysDictTypeService.page(new Page<>(pageNum, pageSize)));
    }

}

