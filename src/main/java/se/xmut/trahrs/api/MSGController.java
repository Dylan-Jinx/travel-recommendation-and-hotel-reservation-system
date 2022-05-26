package se.xmut.trahrs.api;


import com.aliyun.credentials.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.xmut.trahrs.service.MSGService;
import se.xmut.trahrs.util.RandomUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/JWTTest")
public class MSGController {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private MSGService jwtService;


    @GetMapping("/send/{phone}")
    public void sendMsm(@PathVariable String phone){
        // 从redis获取验证码，如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            return;
        }
        // 如果redis获取不到，进行阿里云发送
        //生成随机数
        code = RandomUtil.getFourBitRandom();
        Map map = new HashMap();
        map.put("code",code);
        boolean b = jwtService.send(map,phone);
        if (b){
            //如果发送成功，就把验证码存到redis里，设置5分钟有效时间
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            System.out.println("succeed");
            System.out.println("验证码为"+code);
        }else {
            System.out.println("fail");
        }
    }
}
