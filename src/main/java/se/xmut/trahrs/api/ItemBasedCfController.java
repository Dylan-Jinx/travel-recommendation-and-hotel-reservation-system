package se.xmut.trahrs.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.ItemBasedCfService;
import se.xmut.trahrs.domain.model.ItemBasedCf;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-25
 */
@RestController
@RequestMapping("/itemBasedCf")
public class ItemBasedCfController {

    final Logger logger = LoggerFactory.getLogger(ItemBasedCfController.class);
    @Autowired
    private ItemBasedCfService itemBasedCfService;

    @WebLog(description = "添加")
    @PostMapping
    public ApiResponse save(@RequestBody ItemBasedCf itemBasedCf) {
        return ApiResponse.ok(itemBasedCfService.saveOrUpdate(itemBasedCf));
    }

    @WebLog(description = "用id删除")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(itemBasedCfService.removeById(id));
    }

    @WebLog(description = "查询全部")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(itemBasedCfService.list());
    }

    @WebLog(description = "用id查找")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(itemBasedCfService.getById(id));
    }

    @WebLog(description = "分页")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(itemBasedCfService.page(new Page<>(pageNum, pageSize)));
    }

}

