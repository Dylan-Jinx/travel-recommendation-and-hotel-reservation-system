package se.xmut.trahrs.api;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.domain.dto.InteractionCommentDto;
import se.xmut.trahrs.domain.model.Scene;
import se.xmut.trahrs.domain.model.SceneComment;
import se.xmut.trahrs.filter.SensitiveFilter;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.InteractionCommentService;
import se.xmut.trahrs.domain.model.InteractionComment;
import se.xmut.trahrs.service.SceneCommentService;
import se.xmut.trahrs.service.SceneService;
import se.xmut.trahrs.util.AiAudio;
import se.xmut.trahrs.util.IPUtils;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 * 交流评论 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/interactionComment")
public class InteractionCommentController {

    final Logger logger = LoggerFactory.getLogger(InteractionCommentController.class);
    @Autowired
    private InteractionCommentService interactionCommentService;
    @Autowired
    private SceneCommentService sceneCommentService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SensitiveFilter sensitiveFilter;
    @Autowired
    private SceneService sceneService;
    @Autowired
    private AiAudio aiAudio;
    @WebLog(description = "添加交流评论")
    @PostMapping
    public ApiResponse save(@RequestBody InteractionCommentDto interactionCommentDto,
                            @RequestParam String sid, HttpServletRequest request) {
        InteractionComment interactionComment=modelMapper.map(interactionCommentDto,InteractionComment.class);
        interactionComment.setCommentId(IdUtil.objectId());
        interactionComment.setCreateTime(LocalDateTime.now());
        interactionComment.setReportStatus(0);
        interactionComment.setIp(IPUtils.getIpAddr(request));
        QueryWrapper<Scene> sceneQueryWrapper=new QueryWrapper<>();
        //判断是不是景点的评论
        sceneQueryWrapper.eq("scene_id",sid);
        Scene scene=sceneService.getOne(sceneQueryWrapper);
        //0表示酒店交流评论，1表示景点交流评论
        if (scene ==null){
          interactionComment.setHotelId(sid);
         interactionComment.setFlag(1);
        }else{
            interactionComment.setSceneId(sid);
            interactionComment.setFlag(0);
        }
        String content=interactionComment.getContent();
        if(!StringUtils.isBlank(content)){
            content=sensitiveFilter.filter(content);
            interactionComment.setContent(content);
        }
        return ApiResponse.ok(interactionCommentService.saveOrUpdate(interactionComment));
    }

    @WebLog(description = "用id删除交流评论")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(interactionCommentService.removeById(id));
    }

    @WebLog(description = "查询全部交流评论")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(interactionCommentService.list());
    }

    @WebLog(description = "用id查找交流评论")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(interactionCommentService.getById(id));
    }

    @WebLog(description = "分页交流评论")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(interactionCommentService.page(new Page<>(pageNum, pageSize)));
    }
    @WebLog(description = "根据sceneid查找它的交流评论")
    @GetMapping("/findSceneContent")
    public ApiResponse findScenecontent(@RequestParam String sceneId){
        QueryWrapper<InteractionComment> interactionCommentQueryWrapper=new QueryWrapper<>();
        interactionCommentQueryWrapper.eq("scene_id",sceneId);
        return ApiResponse.ok(interactionCommentService.list(interactionCommentQueryWrapper));
    }
    @WebLog(description = "根据hotelid查找它的交流评论")
    @GetMapping("/findHotelContent")
    public ApiResponse findHotelcontent(@RequestParam String hotelId){
        QueryWrapper<InteractionComment> interactionCommentQueryWrapper=new QueryWrapper<>();
        interactionCommentQueryWrapper.eq("hotel_id",hotelId);
        return ApiResponse.ok(interactionCommentService.list(interactionCommentQueryWrapper));
    }
    @WebLog(description = "模糊查询交流评论")
    @GetMapping("/like")
    public ApiResponse likeContent(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam String content){
        QueryWrapper<InteractionComment> interactionCommentQueryWrapper=new QueryWrapper<>();
        interactionCommentQueryWrapper.like("content",content);
        return ApiResponse.ok(interactionCommentService.page(new Page<>(pageNum, pageSize),interactionCommentQueryWrapper));
    }
    @WebLog(description = "查询酒店的全部交流评论")
    @GetMapping("/flag0")
    public ApiResponse flag0(@RequestParam Integer pageNum,
                             @RequestParam Integer pageSize){
        QueryWrapper<InteractionComment> interactionCommentQueryWrapper=new QueryWrapper<>();
        interactionCommentQueryWrapper.eq("flag",0);
        return ApiResponse.ok(interactionCommentService.page(new Page<>(pageNum, pageSize),interactionCommentQueryWrapper));

    }
    @WebLog(description = "查询景点的全部交流评论")
    @GetMapping("/flag1")
    public ApiResponse flag1(@RequestParam Integer pageNum,
                             @RequestParam Integer pageSize){
        QueryWrapper<InteractionComment> interactionCommentQueryWrapper=new QueryWrapper<>();
        interactionCommentQueryWrapper.eq("flag",1);
        return ApiResponse.ok(interactionCommentService.page(new Page<>(pageNum, pageSize),interactionCommentQueryWrapper));
    }
    @WebLog(description = "举报交流评论")
    @PutMapping
    public ApiResponse reportContent(@RequestParam String commentId){
        QueryWrapper<InteractionComment> interactionCommentQueryWrapper=new QueryWrapper<>();
        interactionCommentQueryWrapper.eq("comment_id",commentId);
        InteractionComment interactionComment=new InteractionComment();
        interactionComment.setReportStatus(1);
        return ApiResponse.ok(interactionCommentService.update(interactionComment,interactionCommentQueryWrapper));


    }
    @WebLog(description = "被举报交流评论")
    @GetMapping("findreportContent")
    public ApiResponse findreportContent(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize
                                     ){
        QueryWrapper<InteractionComment> interactionCommentQueryWrapper=new QueryWrapper<>();
        interactionCommentQueryWrapper.eq("report_status",1);
        return ApiResponse.ok(interactionCommentService.page(new Page<>(pageNum, pageSize),interactionCommentQueryWrapper));
    }
    @WebLog(description = "语音识别")
    @GetMapping("/audio")
    public ApiResponse Aiaudio(){
        return ApiResponse.ok(aiAudio.getAudioText());
    }
}

