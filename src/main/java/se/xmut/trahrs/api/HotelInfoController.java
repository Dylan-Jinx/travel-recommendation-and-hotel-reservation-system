package se.xmut.trahrs.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.HotelInfoService;
import se.xmut.trahrs.domain.model.HotelInfo;


/**
 * <p>
 * 酒店信息 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/hotelInfo")
public class HotelInfoController {

    final Logger logger = LoggerFactory.getLogger(HotelInfoController.class);
    @Autowired
    private HotelInfoService hotelInfoService;

    @WebLog(description = "添加酒店信息")
    @PostMapping
    public ApiResponse save(@RequestBody HotelInfo hotelInfo) {
        return ApiResponse.ok(hotelInfoService.saveOrUpdate(hotelInfo));
    }

    @WebLog(description = "用id删除酒店信息")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(hotelInfoService.removeById(id));
    }

    @WebLog(description = "查询全部酒店信息")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(hotelInfoService.list());
    }

    @WebLog(description = "用id查找酒店信息")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(hotelInfoService.getById(id));
    }

    @WebLog(description = "分页酒店信息")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(hotelInfoService.page(new Page<>(pageNum, pageSize)));
    }

}

