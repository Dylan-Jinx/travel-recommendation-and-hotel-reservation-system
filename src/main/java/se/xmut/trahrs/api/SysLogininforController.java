package se.xmut.trahrs.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.SysLogininforService;
import se.xmut.trahrs.domain.model.SysLogininfor;


/**
 * <p>
 * 系统访问记录 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
@RestController
@RequestMapping("/sysLogininfor")
public class SysLogininforController {

    final Logger logger = LoggerFactory.getLogger(SysLogininforController.class);
    @Autowired
    private SysLogininforService sysLogininforService;

    @WebLog(description = "添加系统访问记录")
    @PostMapping
    public ApiResponse save(@RequestBody SysLogininfor sysLogininfor) {
        return ApiResponse.ok(sysLogininforService.saveOrUpdate(sysLogininfor));
    }

    @WebLog(description = "用id删除系统访问记录")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(sysLogininforService.removeById(id));
    }

    @WebLog(description = "查询全部系统访问记录")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(sysLogininforService.list());
    }

    @WebLog(description = "用id查找系统访问记录")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(sysLogininforService.list());
    }

    @WebLog(description = "分页系统访问记录")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(sysLogininforService.page(new Page<>(pageNum, pageSize)));
    }

}

