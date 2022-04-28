package se.xmut.trahrs.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.LikeRecordService;
import se.xmut.trahrs.domain.model.LikeRecord;


/**
 * <p>
 * 点赞记录 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-29
 */
@RestController
@RequestMapping("/likeRecord")
public class LikeRecordController {

    final Logger logger = LoggerFactory.getLogger(LikeRecordController.class);
    @Autowired
    private LikeRecordService likeRecordService;

    @WebLog(description = "添加点赞记录")
    @PostMapping
    public ApiResponse save(@RequestBody LikeRecord likeRecord) {
        return ApiResponse.ok(likeRecordService.saveOrUpdate(likeRecord));
    }

    @WebLog(description = "用id删除点赞记录")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(likeRecordService.removeById(id));
    }

    @WebLog(description = "查询全部点赞记录")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(likeRecordService.list());
    }

    @WebLog(description = "用id查找点赞记录")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(likeRecordService.list());
    }

    @WebLog(description = "分页点赞记录")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(likeRecordService.page(new Page<>(pageNum, pageSize)));
    }

}

