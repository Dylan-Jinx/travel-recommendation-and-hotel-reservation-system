package se.xmut.trahrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.service.SceneDetailService;
import se.xmut.trahrs.entity.SceneDetail;


/**
 * <p>
 * 景点信息详细（主要是图片） 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/sceneDetail")
public class SceneDetailController {

    final Logger logger = LoggerFactory.getLogger(SceneDetailController.class);
    @Autowired
    private SceneDetailService sceneDetailService;

    @PostMapping
    public ApiResponse save(@RequestBody SceneDetail sceneDetail) {
        return ApiResponse.ok(sceneDetailService.saveOrUpdate(sceneDetail));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(sceneDetailService.removeById(id));
    }

    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(sceneDetailService.list());
    }

    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(sceneDetailService.list());
    }

    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(sceneDetailService.page(new Page<>(pageNum, pageSize)));
    }

}

