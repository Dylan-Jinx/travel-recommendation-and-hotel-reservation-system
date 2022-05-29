package se.xmut.trahrs.service;


import cn.hutool.bloomfilter.BitMapBloomFilter;
import com.google.common.hash.Hashing;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.xmut.trahrs.domain.model.HotelInfo;
import se.xmut.trahrs.domain.model.Scene;
import se.xmut.trahrs.domain.vo.HotelInfoVo;
import se.xmut.trahrs.mapper.HotelInfoMapper;
import se.xmut.trahrs.util.MapUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author breeze
 * @date 2022/5/21 13:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class SceneServiceTest {
    @Autowired
    private SceneService sceneService;
    @Autowired
    private HotelInfoMapper hotelInfoMapper;
    @Autowired
    private MapUtils mapUtils;

    private List<Scene> list = new ArrayList<>();
    @BeforeEach
    public void setUp(){
        Scene scene = new Scene();
        scene.setName("皓月园");
        scene.setLocation("118.075936,24.441099");
        list.add(scene);

        Scene scene2 = new Scene();
        scene2.setName("鼓浪屿");
        scene2.setLocation("118.06702,24.444695");
        list.add(scene2);

        Scene scene3 = new Scene();
        scene3.setName("胡里山炮台");
        scene3.setLocation("118.106383,24.429475");
        list.add(scene3);

        Scene scene4 = new Scene();
        scene4.setName("南普陀寺");
        scene4.setLocation("118.096582,24.440987");
        list.add(scene4);

        Scene scene5 = new Scene();
        scene5.setName("椰风寨");
        scene5.setLocation("118.161867,24.442613");
        list.add(scene5);

        Scene scene6 = new Scene();
        scene6.setName("厦门同安影视城");
        scene6.setLocation("118.165286,24.744326");
        list.add(scene6);

        Scene scene7 = new Scene();
        scene7.setName("江头公园西园");
        scene7.setLocation("118.128956,24.494337");
        list.add(scene7);

        Scene scene8 = new Scene();
        scene8.setName("陈嘉庚纪念胜地-集美鳌园");
        scene8.setLocation("118.106610,24.568555");
        list.add(scene8);

        Scene scene9 = new Scene();
        scene9.setName("天竺山森林公园");
        scene9.setLocation("117.917172,24.59997");
        list.add(scene9);

        Scene scene10 = new Scene();
        scene10.setName("城隍庙");
        scene10.setLocation("118.247520,24.667304");
        list.add(scene10);
    }

    @Test
    public void getNearestHotel() {
        List<HotelInfoVo> nearestHotel = sceneService.getNearestHotel(list, 1000.0);
        for (HotelInfoVo objs:nearestHotel) {
            System.out.println(objs+" "+objs.getSumDistance());
        }
    }

    @Test
    public void testGetNearbyHotelByScene() {
        Scene scene = new Scene();
        scene.setName("鼓浪屿");
        scene.setLocation("118.06702,24.444695");
        List<HotelInfoVo> list = sceneService.getNearbyHotelByScene(scene, 1000.0);
        for (HotelInfoVo objs:list) {
            System.out.println(objs+" "+objs.getSumDistance());
        }

    }

    @Test
    public void testSceneNearbyHotelWithComprehensiveRecommendation(){
        List<HotelInfoVo> nearestHotel = sceneService.getNearestHotel(list, 1000.0);
        List<HotelInfoVo> crHotel = sceneService.getSceneNearbyHotelWithComprehensiveRecommendation(nearestHotel);
        for (HotelInfoVo objs:crHotel) {
            System.out.println(objs+" "+objs.getSumDistance()+" "+objs.getComprehensiveRating());
        }
    }

    @Test
    public void testGetNearbyHotelHighestRatingRecommendation(){
        List<HotelInfoVo> nearestHotel = sceneService.getNearestHotel(list, 1000.0);
        List<HotelInfoVo> hrrHotel = sceneService.getSceneNearbyHotelWithHighestRatingRecommendation(nearestHotel);
        for (HotelInfoVo objs:hrrHotel) {
            System.out.println(objs+" "+objs.getSumDistance()+" "+objs.getRating());
        }
    }

    @Test
    public void testGetNearbyHotelLowestRatingRecommendation(){
        List<HotelInfoVo> nearestHotel = sceneService.getNearestHotel(list, 1000.0);
        List<HotelInfoVo> hrrHotel = sceneService.getSceneNearbyHotelWithLowestRatingRecommendation(nearestHotel);
        for (HotelInfoVo objs:hrrHotel) {
            System.out.println(objs+" "+objs.getSumDistance()+" "+objs.getRating());
        }
    }

    @Test
    public void testCollaborativeFilteringScene() throws TasteException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setUser("root");
        dataSource.setPassword("225579qq");
        dataSource.setDatabaseName("ItemCF");
        JDBCDataModel model = new MySQLJDBCDataModel(dataSource, "ItemCF", "user_id", "item_id", "value", "timestamp");

        ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);
        GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(model, similarity);

        System.out.println("——————————物品——————————");
        List<RecommendedItem> list = recommender.recommendedBecause(2,2,2);
        list.forEach(System.out::println);
        System.out.println();
        System.out.println();
        list = recommender.recommend(7, 5);
        list.forEach(System.out::println);
        System.out.println("——————————物品END——————————");
    }
}