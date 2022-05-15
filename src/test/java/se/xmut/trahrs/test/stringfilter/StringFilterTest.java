package se.xmut.trahrs.test.stringfilter;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.xmut.trahrs.filter.SensitiveFilter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StringFilterTest {

    @Autowired
    private SensitiveFilter sensitiveFilter;
    @Test
    public void textFilterTest() throws Exception{

        final String text = "我很喜欢你这个傻逼六四时间啊pornPorn真的是一个大傻瓜，我真的有点无语你这个大傻步，但是逼傻了我。";
        sensitiveFilter = new SensitiveFilter();
        System.out.println(sensitiveFilter.filter(text));
    }

}
