package se.xmut.trahrs.api;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.domain.dto.HotelCommentDto;
import se.xmut.trahrs.filter.SensitiveFilter;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.HotelCommentService;
import se.xmut.trahrs.domain.model.HotelComment;
import se.xmut.trahrs.util.AiAudio;
import se.xmut.trahrs.util.SemanticUtils;


/**
 * <p>
 * 酒店评论 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/hotelComment")
public class HotelCommentController {

    final Logger logger = LoggerFactory.getLogger(HotelCommentController.class);
    @Autowired
    private HotelCommentService hotelCommentService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SensitiveFilter sensitiveFilter;
    @Autowired
    private SemanticUtils semanticUtils;
    @Autowired
    private AiAudio aiAudio;

    @WebLog(description = "添加酒店评论")
    @PostMapping
    public ApiResponse save(@RequestBody HotelCommentDto hotelCommentDto) {

        HotelComment hotelComment = modelMapper.map(hotelCommentDto,HotelComment.class);
        hotelComment.setCommentId(IdUtil.objectId());
        hotelComment.setCommentTime(LocalDateTimeUtil.now());
        //0就是没有举报
        hotelComment.setReportStatus(0);
        String content = hotelComment.getContent();
        String isSensitive = content;
        if(!StringUtils.isBlank(content)){
            content = sensitiveFilter.filter(content);
            hotelComment.setContent(content);
            if(isSensitive.equals(content)){
                hotelComment.setSemantic(semanticUtils.getSemanticAnalysisResult(content));
            }
        }

        return ApiResponse.ok("操作成功",hotelCommentService.save(hotelComment));
    }

    @WebLog(description = "举报评论内容")
    @PutMapping
    public ApiResponse reportContent(@RequestParam("comment_id") String commentId){
        QueryWrapper<HotelComment> hotelCommentQueryWrapper = new QueryWrapper<>();
        hotelCommentQueryWrapper.eq("comment_id",commentId);
        HotelComment hotelComment = new HotelComment();
        //1就是有人举报
        hotelComment.setReportStatus(1);
        hotelCommentService.update(hotelComment,hotelCommentQueryWrapper);
        return ApiResponse.ok("操作成功");
    }

    @WebLog(description = "被举报的酒店评论")
    @GetMapping("/findReportComment")
    public ApiResponse findReportComment(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        QueryWrapper<HotelComment> hotelCommentQueryWrapper = new QueryWrapper<>();
        hotelCommentQueryWrapper.eq("report_status",1);

        return ApiResponse.ok(hotelCommentService.page(new Page<>(pageNum, pageSize), hotelCommentQueryWrapper));
    }

    @WebLog(description = "用id删除酒店评论")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(hotelCommentService.removeById(id));
    }

    @WebLog(description = "查询全部酒店评论")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(hotelCommentService.list());
    }

    @WebLog(description = "用id查找酒店评论")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable("id") Integer id) {
        return ApiResponse.ok(hotelCommentService.getById(id));
    }

    @WebLog(description = "分页酒店评论")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(hotelCommentService.page(new Page<>(pageNum, pageSize)));
    }
    @WebLog(description = "根据评论内容模糊查询")
    @GetMapping("/like")
    public ApiResponse findContent(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam String content){
        QueryWrapper<HotelComment> hotelCommentQueryWrapper=new QueryWrapper<>();
        hotelCommentQueryWrapper.like("content",content);
        return ApiResponse.ok(hotelCommentService.page(new Page<>(pageNum, pageSize),hotelCommentQueryWrapper));
    }
    @WebLog(description = "语音识别")
    @GetMapping("/audio")
    public ApiResponse Aiaudio(){
        return ApiResponse.ok(aiAudio.getAudioText());
    }
}

