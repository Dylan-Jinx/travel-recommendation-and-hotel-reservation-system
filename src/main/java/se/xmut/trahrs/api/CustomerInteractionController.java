package se.xmut.trahrs.api;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.domain.dto.CustomerInteractionDto;
import se.xmut.trahrs.domain.dto.LikeRecordDto;
import se.xmut.trahrs.domain.model.Customer;
import se.xmut.trahrs.domain.model.LikeRecord;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.CustomerInteractionService;
import se.xmut.trahrs.domain.model.CustomerInteraction;
import se.xmut.trahrs.service.LikeRecordService;
import se.xmut.trahrs.util.IPUtils;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 * 交流记录 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/customerInteraction")
public class CustomerInteractionController {

    final Logger logger = LoggerFactory.getLogger(CustomerInteractionController.class);
    @Autowired
    private CustomerInteractionService customerInteractionService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LikeRecordService likeRecordService;
    @WebLog(description = "添加用户游记")
    @PostMapping
    public ApiResponse save(@RequestBody CustomerInteractionDto customerInteractionDto, HttpServletRequest request) {
        CustomerInteraction customerInteraction= modelMapper.map(customerInteractionDto,CustomerInteraction.class);
        customerInteraction.setTitleId(IdUtil.objectId());
        customerInteraction.setIp(IPUtils.getIpAddr(request));
        customerInteraction.setCreateTime(LocalDateTime.now());
        customerInteraction.setLikeCount(0);
        customerInteraction.setReportStatus(0);

        return ApiResponse.ok(customerInteractionService.saveOrUpdate(customerInteraction));
    }

    @WebLog(description = "用id删除游记")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(customerInteractionService.removeById(id));
    }

    @WebLog(description = "查询全部游记")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(customerInteractionService.list());
    }

    @WebLog(description = "用id查找游记")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(customerInteractionService.getById(id));
    }

    @WebLog(description = "分页交流记录")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(customerInteractionService.page(new Page<>(pageNum, pageSize)));
    }
    @WebLog(description = "举报游记")
    @PutMapping
    public  ApiResponse Status(@RequestParam String titleId){
        QueryWrapper<CustomerInteraction> customerInteractionQueryWrapper=new QueryWrapper<>();
        customerInteractionQueryWrapper.eq("title_id",titleId);
        CustomerInteraction customerInteraction=new CustomerInteraction();
        customerInteraction.setReportStatus(1);
        return  ApiResponse.ok(customerInteractionService.update(customerInteraction,customerInteractionQueryWrapper));
    }
    @WebLog(description = "分页查看举报的游记")
    @GetMapping("/getStatus")
    public  ApiResponse getStatus(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize){
        QueryWrapper<CustomerInteraction> customerInteractionQueryWrapper=new QueryWrapper<>();
        customerInteractionQueryWrapper.eq("report_status",0);
        return ApiResponse.ok(customerInteractionService.page(new Page<>(pageNum, pageSize),customerInteractionQueryWrapper));

    }
    @WebLog(description = "点赞游记")
    @PostMapping("/likecount")
    public ApiResponse likeCount(@RequestBody LikeRecordDto likeRecordDto){
        LikeRecord likeRecord1=modelMapper.map(likeRecordDto,LikeRecord.class);
        QueryWrapper<LikeRecord> likeRecordQueryWrapper=new QueryWrapper<>();
        likeRecordQueryWrapper.eq("customer_id",likeRecord1.getCustomerId()).eq("record_id",likeRecord1.getRecordId());
        LikeRecord likeRecord=likeRecordService.getOne(likeRecordQueryWrapper);
        if(likeRecord==null){
            likeRecordService.save(likeRecord1);
            QueryWrapper<LikeRecord> likeRecordQueryWrapper1=new QueryWrapper<>();
            likeRecordQueryWrapper1.eq("record_id",likeRecord1.getRecordId());
            Long count=likeRecordService.count(likeRecordQueryWrapper1);
            QueryWrapper<CustomerInteraction> customerInteractionQueryWrapper=new QueryWrapper<>();
            customerInteractionQueryWrapper.eq("title_id",likeRecord1.getRecordId());
            CustomerInteraction customerInteraction=new CustomerInteraction();
            int cnt=count.intValue();
            customerInteraction.setLikeCount(cnt);
            customerInteractionService.update(customerInteraction,customerInteractionQueryWrapper);
            return ApiResponse.ok("点赞成功");
        }
        else {
            return ApiResponse.error("点赞失败");
        }
    }
//    @WebLog(description = "按照最近时间推荐游记")
//    @GetMapping("/nearTime")
//    public ApiResponse nearTime(@RequestParam Integer pageNum,
//                                @RequestParam Integer pageSize){
//        QueryWrapper<CustomerInteraction> customerInteractionQueryWrapper=new QueryWrapper<>();
//        customerInteractionQueryWrapper.orderByDesc("create_time");
//        return ApiResponse.ok(customerInteractionService.page(new Page<>(pageNum,pageSize),customerInteractionQueryWrapper));
//    }
//    @WebLog(description = "按照点赞次数推荐")
//    @GetMapping("/count")
//    public ApiResponse count(@RequestParam Integer pageNum,
//                             @RequestParam Integer pageSize){
//        QueryWrapper<CustomerInteraction> customerInteractionQueryWrapper=new QueryWrapper<>();
//        customerInteractionQueryWrapper.orderByDesc("like_count");
//        return ApiResponse.ok(customerInteractionService.page(new Page<>(pageNum,pageSize),customerInteractionQueryWrapper));
//
//    }
    @WebLog(description = "根据游记id查询游记详细")
    @GetMapping("/findByid")
    public ApiResponse findByid(@RequestParam String titleId){
        QueryWrapper<CustomerInteraction> customerInteractionQueryWrapper=new QueryWrapper<>();
        customerInteractionQueryWrapper.eq("title_id",titleId);
        return ApiResponse.ok(customerInteractionService.getOne(customerInteractionQueryWrapper));
    }
    @WebLog(description = "推荐点赞数量多的游记")
    @GetMapping("/showTitle")
    public ApiResponse showTitle(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize){
        Page<Customer> page=new Page<>(pageNum,pageSize);
        IPage<Customer> customerIPage=customerInteractionService.findCustomerInteraction(page);
        List<Customer> customers=customerIPage.getRecords();
        return ApiResponse.ok(customers);
    }
    @WebLog(description = "推荐最近时间的游记")
    @GetMapping("/showCreateTime")
    public ApiResponse showCreateTime(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize){
        Page<Customer> page=new Page<>(pageNum,pageSize);
        IPage<Customer> customerIPage=customerInteractionService.findCustomerInteractionCreateTime(page);
        List<Customer> customers=customerIPage.getRecords();
        return ApiResponse.ok(customers);
    }
    @WebLog(description = "用户查看自己的游记")
    @GetMapping("/findCustomerId")
    public ApiResponse findCustomerId(@RequestParam String customerId){
        QueryWrapper<CustomerInteraction> customerInteractionQueryWrapper=new QueryWrapper<>();
        customerInteractionQueryWrapper.eq("customer_id",customerId);
        return ApiResponse.ok(customerInteractionService.list(customerInteractionQueryWrapper));
    }

}

