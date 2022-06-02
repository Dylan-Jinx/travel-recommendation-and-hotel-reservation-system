package se.xmut.trahrs;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.domain.model.Customer;
import se.xmut.trahrs.domain.model.HotelInfo;
import se.xmut.trahrs.domain.model.Scene;
import se.xmut.trahrs.domain.model.SceneComment;
import se.xmut.trahrs.filter.SensitiveFilter;
import se.xmut.trahrs.service.*;
import se.xmut.trahrs.util.SemanticUtils;

import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
class TravelRecommendationAndHotelReservationSystemApplicationTests {

    @Autowired
    private HotelInfoService hotelInfoService;

    @Autowired
    private SceneCommentService sceneCommentService;

    @Autowired
    private SceneService sceneService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Autowired
    private SemanticUtils semanticUtils;

    @Autowired
    private ItemBasedCfService itemBasedCfService;

    @Test
    void contextLoads() {
        Random random = new Random();

        List<Scene> scenes = sceneService.list();

        String path = "/Users/breeze/Downloads/j2ee期末项目爬虫数据代码/Ctrip_Crawler/data/comments/";

        CsvReader reader = CsvUtil.getReader();

//        CsvData id = reader.read(FileUtil.file((path+"fake_id.csv")));
//        CsvData phone = reader.read(FileUtil.file(path+"fake_phone.csv"));
//        CsvData username = reader.read(FileUtil.file(path+"fake_username.csv"));
//        List<CsvRow> idrows = id.getRows();
//        List<CsvRow> phonerows = phone.getRows();
//        List<CsvRow> usernamerows = username.getRows();
//
//        String[] gender = new String[]{"男","女"};
//        String[] prof = new String[]{"学生","程序员","老师"};
//        String[] area = new String[]{"福建省厦门市","福建省福州市","福建省泉州市","福建省漳州市"};
//        String[] portrait = new String[]{"物园","公园","国家级景点","寺庙","海滩"};

//        for (int i = 0; i < idrows.size(); i++) {
////            System.out.println(idrows.get(i).get(0));
////            System.out.println(phonerows.get(i).get(0));
////            System.out.println(usernamerows.get(i).get(0));
//            QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
//            customerQueryWrapper.eq("phone",phonerows.get(i).get(0));
//
//            if(!ObjectUtil.isNull(customerService.getOne(customerQueryWrapper))){
//                continue;
//            }
//            Customer customer = new Customer();
//            customer.setCustomerName(usernamerows.get(i).get(0));
//            customer.setPhone(phonerows.get(i).get(0));
//            customer.setGender(gender[random.nextInt(2)]);
//            customer.setProfession(prof[random.nextInt(3)]);
//            customer.setArea(area[random.nextInt(4)]);
//            customer.setAvatar("62981e78a7b6f872ca06ef13.png");
//            customer.setCustomerPwd(MD5.create().digestHex("123456"));
//            customer.setIdentity(idrows.get(i).get(0));
//            customer.setCustomerId(IdUtil.objectId());
//            customer.setCreateTime(LocalDateTimeUtil.now());
//            customer.setRemoveFlag(0);
//            Map<String, Object> map = new HashMap<>();
//            map.put(portrait[random.nextInt(5)], 0.75);
//            map.put(portrait[random.nextInt(5)], 0.75);
//            for (int j = 0; j < portrait.length; j++) {
//                if(map.containsKey(portrait[j])){
//                    continue;
//                }else {
//                    map.put(portrait[j], 0);
//                }
//            }
//            JSONObject jsonObject = JSONUtil.parseObj(map);
//            customer.setCustomerPortrait(JSONUtil.toJsonStr(jsonObject));
//            customerService.save(customer);
//
//        }

        List<Customer> customers = customerService.list();

        for (Scene scene : scenes) {
            String newpath = path + scene.getName() + ".csv";
            System.out.println(newpath);
            try {
                CsvData data = reader.read(FileUtil.file(newpath));
                List<CsvRow> rows = data.getRows();
                System.out.println(rows);

                for (int i=0;i<rows.size();i++) {
                    System.out.println(rows.get(i));
                    //getRawList返回一个List列表，列表的每一项为CSV中的一个单元格（既逗号分隔部分
                    List<Integer> list = new ArrayList<>();
                    list.add(random.nextInt(10));
                    list.add(random.nextInt(7) + 3);
                    list.add(random.nextInt(5) + 5);
                    Customer customer = customers.get(random.nextInt(customers.size()));
                    SceneComment sceneComment = new SceneComment();
                    sceneComment.setCommentId(IdUtil.objectId());
                    sceneComment.setCommentTime(LocalDateTime.now());
                    sceneComment.setReportStatus(0);
                    sceneComment.setSceneId(scene.getSceneId());
                    sceneComment.setCustomerId(customer.getCustomerId());
                    sceneComment.setContent(rows.get(i).get(1));
                    sceneComment.setStar(list.get(random.nextInt(3)));

                    //敏感词过滤
                    String content = sceneComment.getContent();
                    String isSensitive = content;
                    if (!StringUtils.isBlank(content)) {
                        content = sensitiveFilter.filter(content);
                        sceneComment.setContent(content);
                        //如果没有敏感词进行情感分析
                        if (isSensitive.equals(content)) {
                            sceneComment.setSemantic(semanticUtils.getSemanticAnalysisResult(content));
                        }

                        //写入cf的csv中，并将redis中这个人的已推荐排除全部重置

                        itemBasedCfService.writeCustomerPreference((long) customer.getId(),
                                (long) scene.getId(), ((float) sceneComment.getStar() / 2.0F));
//                        redisTemplate.delete(sceneCommentDto.getCustomerPK()+"");
//                    System.out.println(csvRow.get(1));


                    }
                    sceneCommentService.save(sceneComment);
                }
            } catch (Exception e) {
//                e.printStackTrace();
            }

        }


//        List<HotelInfo> hotelInfos = hotelInfoService.list();

//        for (HotelInfo hotelInfo:hotelInfos) {
//            List<Integer> integerList = new ArrayList<>();
//            integerList.add((random.nextInt(30)+10)*10+9+random.nextInt(9)*10);
//            integerList.add((((random.nextInt(20)+10)*random.nextInt(50)+10)/10)*10+100+9+random.nextInt(9)*10);
//            integerList.add((random.nextInt(50)+20)*10+9+random.nextInt(9)*10);
////            System.out.println(hotelInfo);
//            Collections.sort(integerList);
//            String s = hotelInfo.getKeyTag();
//            if(s.contains("星") || s.contains("豪") || s.contains("高")){
//                hotelInfo.setCost(integerList.get(2));
//            } else if ( s.contains("商") || s.contains("主")) {
//                hotelInfo.setCost(integerList.get(1));
//            }else {
//                hotelInfo.setCost(integerList.get(0));
//            }
//            hotelInfoService.getBaseMapper().updateById(hotelInfo);
//        }

    }
}


