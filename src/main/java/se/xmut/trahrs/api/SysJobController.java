package se.xmut.trahrs.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.SysJobService;
import se.xmut.trahrs.domain.model.SysJob;


/**
 * <p>
 * 定时任务调度表 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/sysJob")
public class SysJobController {

    final Logger logger = LoggerFactory.getLogger(SysJobController.class);
    @Autowired
    private SysJobService sysJobService;

    @WebLog(description = "添加定时任务调度表")
    @PostMapping
    public ApiResponse save(@RequestBody SysJob sysJob) {
        return ApiResponse.ok(sysJobService.saveOrUpdate(sysJob));
    }

    @WebLog(description = "用id删除定时任务调度表")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(sysJobService.removeById(id));
    }

    @WebLog(description = "查询全部定时任务调度表")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(sysJobService.list());
    }

    @WebLog(description = "用id查找定时任务调度表")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(sysJobService.getById(id));
    }

    @WebLog(description = "分页定时任务调度表")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(sysJobService.page(new Page<>(pageNum, pageSize)));
    }

}

