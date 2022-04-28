package se.xmut.trahrs.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.SceneDetailService;
import se.xmut.trahrs.domain.model.SceneDetail;


/**
 * <p>
 * 景点信息详细（主要是图片） 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
@RestController
@RequestMapping("/sceneDetail")
public class SceneDetailController {

    final Logger logger = LoggerFactory.getLogger(SceneDetailController.class);
    @Autowired
    private SceneDetailService sceneDetailService;

    @WebLog(description = "添加景点信息详细（主要是图片）")
    @PostMapping
    public ApiResponse save(@RequestBody SceneDetail sceneDetail) {
        return ApiResponse.ok(sceneDetailService.saveOrUpdate(sceneDetail));
    }

    @WebLog(description = "用id删除景点信息详细（主要是图片）")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(sceneDetailService.removeById(id));
    }

    @WebLog(description = "查询全部景点信息详细（主要是图片）")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(sceneDetailService.list());
    }

    @WebLog(description = "用id查找景点信息详细（主要是图片）")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(sceneDetailService.list());
    }

    @WebLog(description = "分页景点信息详细（主要是图片）")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(sceneDetailService.page(new Page<>(pageNum, pageSize)));
    }

}

