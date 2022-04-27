package se.xmut.trahrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.service.OtherRoleInfoService;
import se.xmut.trahrs.entity.OtherRoleInfo;


/**
 * <p>
 * 其他角色id（酒店、景区、餐馆、后台管理、） 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/otherRoleInfo")
public class OtherRoleInfoController {

    final Logger logger = LoggerFactory.getLogger(OtherRoleInfoController.class);
    @Autowired
    private OtherRoleInfoService otherRoleInfoService;

    @PostMapping
    public ApiResponse save(@RequestBody OtherRoleInfo otherRoleInfo) {
        return ApiResponse.ok(otherRoleInfoService.saveOrUpdate(otherRoleInfo));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(otherRoleInfoService.removeById(id));
    }

    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(otherRoleInfoService.list());
    }

    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(otherRoleInfoService.list());
    }

    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(otherRoleInfoService.page(new Page<>(pageNum, pageSize)));
    }

}

