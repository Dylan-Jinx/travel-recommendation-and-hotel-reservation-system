package se.xmut.trahrs.util;

import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsSendTest {

    @Autowired
    SmsSend smsSend;
    @Test
    public void send() {
        try {
            smsSend.send("17605944912");
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
    }
}