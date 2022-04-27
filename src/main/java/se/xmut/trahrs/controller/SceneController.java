package se.xmut.trahrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.service.SceneService;
import se.xmut.trahrs.entity.Scene;


/**
 * <p>
 * 景点信息 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/scene")
public class SceneController {

    final Logger logger = LoggerFactory.getLogger(SceneController.class);
    @Autowired
    private SceneService sceneService;

    @PostMapping
    public ApiResponse save(@RequestBody Scene scene) {
        return ApiResponse.ok(sceneService.saveOrUpdate(scene));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(sceneService.removeById(id));
    }

    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(sceneService.list());
    }

    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(sceneService.list());
    }

    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(sceneService.page(new Page<>(pageNum, pageSize)));
    }

}

