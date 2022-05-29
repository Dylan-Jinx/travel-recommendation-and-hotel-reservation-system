package se.xmut.trahrs.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Component
public class SmsSend {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    SmsClient smsClient;

    public HashMap<String,Object> send(String tel) throws TencentCloudSDKException {
        HashMap<String,Object> map = new HashMap<String,Object>();
        //创建短信发送请求对象
        SendSmsRequest req = new SendSmsRequest();
        //接收短信手机号
        String[] phone = {tel};
        req.setPhoneNumberSet(phone);
        //个人短信模板模板Id
        req.setTemplateId("1419758");
        //个人短信发送SDK
        req.setSmsSdkAppId("1400685052");
        //个人短信签名
        req.setSignName("小胖纸z公众号");
        //短信验证码
        String code=RandomUtil.getFourBitRandom();
        String[] p = {code};
        req.setTemplateParamSet(p);
        //发送对象，并获取获取响应对象
        SendSmsResponse resp = smsClient.SendSms(req);
        //将响应结果转换成字符串格式
        String info = SendSmsResponse.toJsonString(resp);
        // 打印结果信息
        System.out.println(info);

        try {
            //将字符串格式转换成Map 接口对象,获取短信发送成功的提示
            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> m = mapper.readValue(info, Map.class);
            List<Map<String,Object>> list =( List<Map<String,Object>>) m.get("SendStatusSet");

            if(list.get(0).get("Code").equals("Ok")){
                //如果发送成功，就把验证码存到redis里，设置5分钟有效时间
                redisTemplate.opsForValue().set(tel,code,5, TimeUnit.MINUTES);

            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return map;
    }
}