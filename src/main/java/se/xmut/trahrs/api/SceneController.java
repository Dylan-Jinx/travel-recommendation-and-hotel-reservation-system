package se.xmut.trahrs.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.SceneService;
import se.xmut.trahrs.domain.model.Scene;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-20
 */
@RestController
@RequestMapping("/scene")
public class SceneController {

    final Logger logger = LoggerFactory.getLogger(SceneController.class);
    @Autowired
    private SceneService sceneService;

    @WebLog(description = "添加")
    @PostMapping
    public ApiResponse save(@RequestBody Scene scene) {
        return ApiResponse.ok(sceneService.saveOrUpdate(scene));
    }

    @WebLog(description = "用id删除")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(sceneService.removeById(id));
    }

    @WebLog(description = "查询全部")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(sceneService.list());
    }

    @WebLog(description = "用id查找")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(sceneService.getById(id));
    }

    @WebLog(description = "分页")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(sceneService.page(new Page<>(pageNum, pageSize)));
    }

}

