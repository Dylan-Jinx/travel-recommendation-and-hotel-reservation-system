package se.xmut.trahrs.api;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.jdbc.Null;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.domain.dto.NoticeDto;
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

    @Autowired
    private ModelMapper modelMapper;

    @WebLog(description = "添加公告")
    @PostMapping("/save")
    public ApiResponse save(@RequestBody NoticeDto noticeDto) {

        Notice notice = modelMapper.map(noticeDto, Notice.class);

        notice.setSort(0);
        notice.setCreateTime(LocalDateTimeUtil.now());
        noticeService.save(notice);

        return ApiResponse.ok("公告添加成功");
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

    @WebLog(description = "公告模糊查询+分页")
    @GetMapping("/findLike")
    public ApiResponse findPage(@RequestParam String likeName, @RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {

        QueryWrapper<Notice> noticeQueryWrapper=new QueryWrapper<>();
        noticeQueryWrapper
                .eq("noticeTitle",likeName);

        List<Notice> noticeList=noticeService.list(noticeQueryWrapper);

        if(noticeList.isEmpty()){
            return  ApiResponse.ok("没有查询公告内容");
        }

        return ApiResponse.ok(noticeService.page(new Page<>(pageNum, pageSize),noticeQueryWrapper));
    }

    @WebLog(description = "插入公告置顶信息！")
            @PutMapping("/updateSort")
    public ApiResponse updateSort(@RequestBody NoticeDto noticeDto) {

        Notice notice=modelMapper.map(noticeDto,Notice.class);

        Integer sort=notice.getSort();

        QueryWrapper<Notice> noticeQueryWrapper=new QueryWrapper<>();
        noticeQueryWrapper
                .eq("sort",sort);

        Notice oldNotice=noticeService.getOne(noticeQueryWrapper);
        oldNotice.setNoticeId(null);
        oldNotice.setSort(0);

        notice.setCreateTime(LocalDateTimeUtil.now());
        noticeService.update(notice,noticeQueryWrapper);  // 更新置顶的前几条的公告
        noticeService.save(oldNotice);          //被顶掉的公告

        return ApiResponse.ok("公告更新成功！");
    }

    @WebLog(description = "根据时间查询公告")
    @GetMapping("/findAllByTime")
    public ApiResponse findAllByTime() {

        QueryWrapper<Notice> noticeQueryWrapper=new QueryWrapper<>();
        noticeQueryWrapper
                .orderByDesc("create_time")
                .eq("sort",0);

        List<Notice> noticeList=noticeService.list(noticeQueryWrapper);

        return ApiResponse.ok(noticeList);
    }


}

