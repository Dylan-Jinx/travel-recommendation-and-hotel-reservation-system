package se.xmut.trahrs.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.mahout.cf.taste.common.TasteException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import se.xmut.trahrs.domain.model.Customer;
import se.xmut.trahrs.domain.model.Scene;
import se.xmut.trahrs.domain.model.SceneComment;
import se.xmut.trahrs.filter.SensitiveFilter;
import se.xmut.trahrs.util.SemanticUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author breeze
 * @date 2022/5/25 23:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class ItemBasedCfServiceTest {

    @Autowired
    ItemBasedCfService itemBasedCfService;
    @Autowired
    SemanticUtils semanticUtils;
    @Autowired
    SensitiveFilter sensitiveFilter;
    @Autowired
    CustomerService customerService;
    @Autowired
    SceneService sceneService;
    @Autowired
    SceneCommentService sceneCommentService;

    @Test
    public void getItemBasedCFRecommendation() throws TasteException, IOException {
        System.out.println(itemBasedCfService.getItemBasedCFRecommendation(54L, 2));
    }

    @Test
    public void guessYouLike() throws TasteException, IOException {
        System.out.println(itemBasedCfService.guessYouLike(54L, 2, null));
    }

    //暂时变成写入景点数据测试
//    @Test
//    public void writeCustomerPreference() throws IOException {
//
////        itemBasedCfService.writeCustomerPreference(2000L, 380L, 4.5F);
//        CsvReader reader = CsvUtil.getReader();
//        //从文件中读取CSV数据
//        CsvData data = reader.read(ResourceUtils.getFile("classpath:CF.csv"));
//        List<CsvRow> rows = data.getRows();
//        //遍历行
//        for (CsvRow csvRow : rows) {
//            //getRawList返回一个List列表，列表的每一项为CSV中的一个单元格（既逗号分隔部分）
//            System.out.println();
//            QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
//            customerQueryWrapper.eq("id", csvRow.get(0));
//            Customer customer = customerService.getOne(customerQueryWrapper);
//            QueryWrapper<Scene> sceneQueryWrapper = new QueryWrapper<>();
//            sceneQueryWrapper.eq("id", csvRow.get(1));
//            Scene scene = sceneService.getOne(sceneQueryWrapper);
//            SceneComment sceneComment = new SceneComment();
//            sceneComment.setCommentId(IdUtil.objectId());
//            sceneComment.setCommentTime(LocalDateTime.now());
//            sceneComment.setReportStatus(0);
//
//            sceneComment.setStar((int)(Double.parseDouble(csvRow.get(2))*2));
//            sceneComment.setCustomerId(customer.getCustomerId());
//            sceneComment.setSceneId(scene.getSceneId());
//            double rating = Double.parseDouble(csvRow.get(2));
//            if(rating>3.5) {
//                sceneComment.setContent("浪漫喔，可以看到漂亮的厦门美景，总体超赞，性价比高，有趣好玩，景色不错");
//            } else if (rating>=2.0 && rating<=3.5) {
//                sceneComment.setContent("景点还可以，商业气息太浓，个人最喜欢看的就是那些具有年代感和历史感的洋楼，唯一遗憾的就是只能看看外观，不能内部参观");
//            }else {
//                sceneComment.setContent("太多智商税，建议不要去，都是坑，不要去踩，看了个寂寞");
//            }
//
//            //敏感词过滤
//            String content=sceneComment.getContent();
//            String isSensitive = content;
//            if(!StringUtils.isBlank(content)){
//                content=sensitiveFilter.filter(content);
//                sceneComment.setContent(content);
//                //如果没有敏感词进行情感分析
//                if(isSensitive.equals(content)){
//                    sceneComment.setSemantic(semanticUtils.getSemanticAnalysisResult(content));
//                }
//            }
//            sceneCommentService.save(sceneComment);
//            Console.log(csvRow.getRawList());
//        }
//
//    }
}