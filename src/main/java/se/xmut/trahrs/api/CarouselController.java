package se.xmut.trahrs.api;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

import java.util.ArrayList;
import java.util.List;


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

    @WebLog(description = "轮播的添加")
    @PostMapping("/save")
    public ApiResponse save(@RequestBody CarouselDto carouselDto) {

        Carousel carousel=modelMapper.map(carouselDto,Carousel.class);

        carousel.setSort(0);
        carousel.setCreateTime(LocalDateTimeUtil.now());

        carouselService.save(carousel);

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

    @WebLog(description = "根据sort修改轮播图的位置")
    @PutMapping("/updatePosition")
    public ApiResponse save_insert(@RequestBody CarouselDto carouselDto) {
        Carousel carousel=modelMapper.map(carouselDto,Carousel.class);
        Integer  sort=carousel.getSort();

        UpdateWrapper<Carousel> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("sort",sort);

        //原先的轮播图
        Carousel originCarousel=carouselService.getOne(updateWrapper);
        originCarousel.setSort(0);
        originCarousel.setId(null);

        carousel.setCreateTime(LocalDateTimeUtil.now());

        carouselService.update(carousel,updateWrapper);
        carouselService.save(originCarousel);

        return ApiResponse.ok("轮播图插入更改成功");

    }

    /***
     * end 展示多少张轮播图
     */

    @WebLog(description = "根据sort升序查询轮播图")
    @GetMapping("/findBySort")
    public ApiResponse findBySort(@RequestParam Integer end) {

        QueryWrapper<Carousel> carouselQueryWrapper=new QueryWrapper<>();
        carouselQueryWrapper
                .ne("sort",0)
                .orderByAsc("sort");

        String sql="limit 0,"+end;
        carouselQueryWrapper.last(sql);


        List<Carousel> carouselList=carouselService.list(carouselQueryWrapper);

        return ApiResponse.ok("查询成功",carouselList);

    }





}

