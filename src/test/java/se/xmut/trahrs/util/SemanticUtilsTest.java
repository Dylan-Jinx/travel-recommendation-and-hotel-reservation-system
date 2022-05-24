package se.xmut.trahrs.util;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author breeze
 * @date 2022/5/23 21:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class SemanticUtilsTest {

    @Autowired
    private SemanticUtils semanticUtils;

    @Test
    public void getSemanticAnalysisResult() {
        System.out.println(semanticUtils.getSemanticAnalysisResult("有专车接送到迪士尼，房东煎鸡蛋很好吃还有南瓜粥也很好吃谢谢房东老板给我升级房间。祝老板以后生意兴隆。"));
    }

}