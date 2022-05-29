package se.xmut.trahrs.config;


import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TencentSmsConfig {

    @Bean
    public SmsClient getSmsCilent(){

        //创建腾讯云短信发送凭证对象，填入secretId和secretKey
        Credential cred = new Credential("AKIDFJ8j4A3h5E9pFQL1RfeNq43mGUTEk01m", "XpreBSUHwTnumOlxta2EBtLYVDguuhGb");

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("sms.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        //创建短信发送服务平台对象
        SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);
        return client;

    }
}