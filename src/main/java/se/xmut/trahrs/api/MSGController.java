package se.xmut.trahrs.api;


import com.aliyun.credentials.utils.StringUtils;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.xmut.trahrs.service.MSGService;
import se.xmut.trahrs.util.RandomUtil;
import se.xmut.trahrs.util.SmsSend;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/SMScode")
public class MSGController {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    SmsSend smsSend;


    @GetMapping("/send/{phone}")
    public void sendMsm(@PathVariable String phone){
        // 从redis获取验证码，如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            return;
        }
        try {
            smsSend.send(phone);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
    }
}
