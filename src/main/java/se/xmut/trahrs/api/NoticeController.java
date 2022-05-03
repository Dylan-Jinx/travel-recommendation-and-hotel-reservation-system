package se.xmut.trahrs.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.NoticeService;
import se.xmut.trahrs.domain.model.Notice;


/**
 * <p>
 * 公告 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    final Logger logger = LoggerFactory.getLogger(NoticeController.class);
    @Autowired
    private NoticeService noticeService;

    @WebLog(description = "添加公告")
    @PostMapping
    public ApiResponse save(@RequestBody Notice notice) {
        return ApiResponse.ok(noticeService.saveOrUpdate(notice));
    }

    @WebLog(description = "用id删除公告")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(noticeService.removeById(id));
    }

    @WebLog(description = "查询全部公告")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(noticeService.list());
    }

    @WebLog(description = "用id查找公告")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(noticeService.getById(id));
    }

    @WebLog(description = "分页公告")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(noticeService.page(new Page<>(pageNum, pageSize)));
    }

}

