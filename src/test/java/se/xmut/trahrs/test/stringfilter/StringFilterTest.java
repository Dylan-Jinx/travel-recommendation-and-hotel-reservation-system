package se.xmut.trahrs.test.stringfilter;


import cn.hutool.core.date.StopWatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.xmut.trahrs.filter.SensitiveFilter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StringFilterTest {

    private final Logger logger = LoggerFactory.getLogger(StringFilterTest.class);
    @Autowired
    private SensitiveFilter sensitiveFilter;
    @Test
    public void textFilterTest() throws Exception{
        final String text = "我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这" +
                "嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语" +
                "你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无" +
                "语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼" +
                "个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是" +
                "逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你" +
                "这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。" +
                "我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这\" +\n" +
                "                \"嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语\" +\n" +
                "                \"你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无\" +\n" +
                "                \"语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼\" +\n" +
                "                \"个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是\" +\n" +
                "                \"逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你\" +\n" +
                "                \"这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。" +
                "我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这\" +\n" +
                "                \"嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语\" +\n" +
                "                \"你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无\" +\n" +
                "                \"语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼\" +\n" +
                "                \"个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是\" +\n" +
                "                \"逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你\" +\n" +
                "                \"这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。" +
                "我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这\" +\n" +
                "                \"嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语\" +\n" +
                "                \"你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无\" +\n" +
                "                \"语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼\" +\n" +
                "                \"个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是\" +\n" +
                "                \"逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你\" +\n" +
                "                \"这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。" +
                "我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这\" +\n" +
                "                \"嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语\" +\n" +
                "                \"你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无\" +\n" +
                "                \"语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼\" +\n" +
                "                \"个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是\" +\n" +
                "                \"逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你\" +\n" +
                "                \"这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。\" +\n" +
                "                \"我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这\\\" +\\n\" +\n" +
                "                \"                \\\"嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语\\\" +\\n\" +\n" +
                "                \"                \\\"你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无\\\" +\\n\" +\n" +
                "                \"                \\\"语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼\\\" +\\n\" +\n" +
                "                \"                \\\"个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是\\\" +\\n\" +\n" +
                "                \"                \\\"逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你\\\" +\\n\" +\n" +
                "                \"                \\\"这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。\" +\n" +
                "                \"我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这\\\" +\\n\" +\n" +
                "                \"                \\\"嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语\\\" +\\n\" +\n" +
                "                \"                \\\"你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无\\\" +\\n\" +\n" +
                "                \"                \\\"语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼\\\" +\\n\" +\n" +
                "                \"                \\\"个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是\\\" +\\n\" +\n" +
                "                \"                \\\"逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你\\\" +\\n\" +\n" +
                "                \"                \\\"这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。\" +\n" +
                "                \"我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这\\\" +\\n\" +\n" +
                "                \"                \\\"嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语\\\" +\\n\" +\n" +
                "                \"                \\\"你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无\\\" +\\n\" +\n" +
                "                \"                \\\"语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼\\\" +\\n\" +\n" +
                "                \"                \\\"个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是\\\" +\\n\" +\n" +
                "                \"                \\\"逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你\\\" +\\n\" +\n" +
                "                \"                \\\"这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。" +
                "我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这\" +\n" +
                "                \"嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语\" +\n" +
                "                \"你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无\" +\n" +
                "                \"语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼\" +\n" +
                "                \"个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是\" +\n" +
                "                \"逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你\" +\n" +
                "                \"这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。\" +\n" +
                "                \"我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这\\\" +\\n\" +\n" +
                "                \"                \\\"嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语\\\" +\\n\" +\n" +
                "                \"                \\\"你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无\\\" +\\n\" +\n" +
                "                \"                \\\"语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼\\\" +\\n\" +\n" +
                "                \"                \\\"个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是\\\" +\\n\" +\n" +
                "                \"                \\\"逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你\\\" +\\n\" +\n" +
                "                \"                \\\"这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。\" +\n" +
                "                \"我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这\\\" +\\n\" +\n" +
                "                \"                \\\"嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语\\\" +\\n\" +\n" +
                "                \"                \\\"你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无\\\" +\\n\" +\n" +
                "                \"                \\\"语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼\\\" +\\n\" +\n" +
                "                \"                \\\"个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是\\\" +\\n\" +\n" +
                "                \"                \\\"逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你\\\" +\\n\" +\n" +
                "                \"                \\\"这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。\" +\n" +
                "                \"我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这\\\" +\\n\" +\n" +
                "                \"                \\\"嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语\\\" +\\n\" +\n" +
                "                \"                \\\"你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无\\\" +\\n\" +\n" +
                "                \"                \\\"语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼\\\" +\\n\" +\n" +
                "                \"                \\\"个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是\\\" +\\n\" +\n" +
                "                \"                \\\"逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你\\\" +\\n\" +\n" +
                "                \"                \\\"这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。" +
                "我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这\" +\n" +
                "                \"嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语\" +\n" +
                "                \"你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无\" +\n" +
                "                \"语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼\" +\n" +
                "                \"个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是\" +\n" +
                "                \"逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你\" +\n" +
                "                \"这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。\" +\n" +
                "                \"我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这\\\" +\\n\" +\n" +
                "                \"                \\\"嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语\\\" +\\n\" +\n" +
                "                \"                \\\"你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无\\\" +\\n\" +\n" +
                "                \"                \\\"语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼\\\" +\\n\" +\n" +
                "                \"                \\\"个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是\\\" +\\n\" +\n" +
                "                \"                \\\"逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你\\\" +\\n\" +\n" +
                "                \"                \\\"这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。\" +\n" +
                "                \"我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这\\\" +\\n\" +\n" +
                "                \"                \\\"嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语\\\" +\\n\" +\n" +
                "                \"                \\\"你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无\\\" +\\n\" +\n" +
                "                \"                \\\"语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼\\\" +\\n\" +\n" +
                "                \"                \\\"个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是\\\" +\\n\" +\n" +
                "                \"                \\\"逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你\\\" +\\n\" +\n" +
                "                \"                \\\"这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。\" +\n" +
                "                \"我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这\\\" +\\n\" +\n" +
                "                \"                \\\"嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语\\\" +\\n\" +\n" +
                "                \"                \\\"你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无\\\" +\\n\" +\n" +
                "                \"                \\\"语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼\\\" +\\n\" +\n" +
                "                \"                \\\"个大傻步，但是逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是\\\" +\\n\" +\n" +
                "                \"                \\\"逼傻了我。我很喜欢你这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。我很喜欢你\\\" +\\n\" +\n" +
                "                \"                \\\"这个傻逼六四时间啊pornPorn真的是cjd一个大cdj傻瓜，我真的有cjdjdjcjdpdjsdp点无语你这嫖娼个大傻步，但是逼傻了我。";


        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    StopWatch stopWatch = new StopWatch();
                    stopWatch.start();
                    sensitiveFilter = new SensitiveFilter();
//                    logger.info(sensitiveFilter.filter(text));
                    stopWatch.stop();
                    logger.info("当前程序运行时间为："+ stopWatch.getTotalTimeSeconds());
                }
            });
            thread.start();
        }
    }

}
