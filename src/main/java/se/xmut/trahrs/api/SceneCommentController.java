package se.xmut.trahrs.api;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import oshi.util.StringUtil;
import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.domain.dto.SceneCommentDto;
import se.xmut.trahrs.domain.vo.HotelCommentVo;
import se.xmut.trahrs.domain.vo.SceneCommentVo;
import se.xmut.trahrs.filter.SensitiveFilter;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.ItemBasedCfService;
import se.xmut.trahrs.service.SceneCommentService;
import se.xmut.trahrs.domain.model.SceneComment;
import se.xmut.trahrs.util.AiAudio;
import se.xmut.trahrs.util.SemanticUtils;


/**
 * <p>
 * 景区评论 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/sceneComment")
public class SceneCommentController {

    final Logger logger = LoggerFactory.getLogger(SceneCommentController.class);
    @Autowired
    private SceneCommentService sceneCommentService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SensitiveFilter sensitiveFilter;
    @Autowired
    private AiAudio aiAudio;
    @Autowired
    private SemanticUtils semanticUtils;
    @Autowired
    private ItemBasedCfService itemBasedCfService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @WebLog(description = "添加景区评论")
    @PostMapping
    public ApiResponse save(@RequestBody SceneCommentDto sceneCommentDto) throws IOException {
        SceneComment sceneComment = modelMapper.map(sceneCommentDto,SceneComment.class);
        sceneComment.setCommentId(IdUtil.objectId());
        sceneComment.setCommentTime(LocalDateTime.now());
        //默认未举报状态
        sceneComment.setReportStatus(0);
        //敏感词过滤
        String content=sceneComment.getContent();
        String isSensitive = content;
        if(!StringUtils.isBlank(content)){
            content=sensitiveFilter.filter(content);
            sceneComment.setContent(content);
            //如果没有敏感词进行情感分析
            if(isSensitive.equals(content)){
                sceneComment.setSemantic(semanticUtils.getSemanticAnalysisResult(content));
            }
            //写入cf的csv中，并将redis中这个人的已推荐排除全部重置
            itemBasedCfService.writeCustomerPreference(sceneCommentDto.getCustomerPK(),
                    sceneCommentDto.getScenePK(), ((float)sceneCommentDto.getStar() / 2.0F));
            redisTemplate.delete(sceneCommentDto.getCustomerPK()+"");
        }

        return ApiResponse.ok(sceneCommentService.saveOrUpdate(sceneComment));
    }

    @WebLog(description = "用id删除景区评论")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(sceneCommentService.removeById(id));
    }

    @WebLog(description = "查询全部景区评论")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(sceneCommentService.list());
    }

    @WebLog(description = "用id查找景区评论")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(sceneCommentService.getById(id));
    }

    @WebLog(description = "分页景区评论")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(sceneCommentService.page(new Page<>(pageNum, pageSize)));
    }
    @WebLog(description = "景点评论模糊查询")
    @GetMapping("/like")
    public ApiResponse likeContent(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam String content){
        QueryWrapper<SceneComment> sceneCommentQueryWrapper=new QueryWrapper<>();
        //模糊查询like
        sceneCommentQueryWrapper.like("content",content);
        return ApiResponse.ok("模糊查询",sceneCommentService.page(new Page<>(pageNum, pageSize),sceneCommentQueryWrapper));

    }
    @WebLog(description = "举报评论内容")
    @PutMapping
    public ApiResponse reporterContent(@RequestParam String commentId){
        QueryWrapper<SceneComment> sceneCommentQueryWrapper=new QueryWrapper<>();
        sceneCommentQueryWrapper.eq("comment_id",commentId);
        SceneComment sceneComment=new SceneComment();
        sceneComment.setReportStatus(1);
        return ApiResponse.ok(sceneCommentService.update(sceneComment,sceneCommentQueryWrapper));
    }
    @WebLog(description = "查找被举报的评论")
    @GetMapping("/findreportContent")
    public ApiResponse findreportContent(@RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize){
        QueryWrapper<SceneComment> sceneCommentQueryWrapper=new QueryWrapper<>();
        sceneCommentQueryWrapper.eq("report_status",1);
        return ApiResponse.ok(sceneCommentService.page(new Page<>(pageNum,pageSize),sceneCommentQueryWrapper));
    }
    @WebLog(description = "语音识别")
    @GetMapping("/audio")
    public ApiResponse Aiaudio(){
        return ApiResponse.ok(aiAudio.getAudioText());
    }
    @WebLog(description = "显示评论")
    @GetMapping("/findSceneComment")
    public ApiResponse findSceneComment(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize){
        Page<SceneCommentVo> page=new Page<>(pageNum,pageSize);
        IPage<SceneCommentVo> hotelCommentVoIPage=sceneCommentService.findScenecomment(page);
        List<SceneCommentVo> hotelCommentVoList=hotelCommentVoIPage.getRecords();
        return ApiResponse.ok(hotelCommentVoList);
    }
}

