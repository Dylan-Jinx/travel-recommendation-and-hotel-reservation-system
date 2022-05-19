package se.xmut.trahrs.api;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.domain.dto.CarouselDto;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.CarouselService;
import se.xmut.trahrs.domain.model.Carousel;


/**
 * <p>
 * 公告轮播图 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/carousel")
public class CarouselController {

    final Logger logger = LoggerFactory.getLogger(CarouselController.class);
    @Autowired
    private CarouselService carouselService;

    @Autowired
    private ModelMapper modelMapper;

    @WebLog(description = "添加公告轮播图")
    @PostMapping("/save")
    public ApiResponse save(@RequestBody CarouselDto carouselDto) {

        Carousel carousel=modelMapper.map(carouselDto,Carousel.class);
        
        Integer sort=carousel.getSort();   // 轮播图的位置
        String imgUrl=carousel.getImgUrl(); // 获取更新的图片的路径

        UpdateWrapper<Carousel> updateWrapper=new UpdateWrapper<>();

        updateWrapper.eq("sort",sort);

        carousel.setImgUrl(imgUrl);
        carousel.setCreateTime(LocalDateTimeUtil.now());

        carouselService.update(carousel,updateWrapper);

        return ApiResponse.ok("轮播图插入成功");

    }

    @WebLog(description = "用id删除公告轮播图")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(carouselService.removeById(id));
    }

    @WebLog(description = "查询全部公告轮播图")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(carouselService.list());
    }

    @WebLog(description = "用id查找公告轮播图")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(carouselService.getById(id));
    }

    @WebLog(description = "分页公告轮播图")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(carouselService.page(new Page<>(pageNum, pageSize)));
    }

}

