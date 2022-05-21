package se.xmut.trahrs.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.xmut.trahrs.domain.model.Scene;
import se.xmut.trahrs.exception.GaoDeException;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Test
    public void getNearestHotel() throws GaoDeException {
        List<Scene> list = new ArrayList<>();
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
        list.add(scene7);
        List<Object[]> nearestHotel = sceneService.getNearestHotel(list, 1000);
        for (Object[] objs:nearestHotel) {
            System.out.println(Arrays.toString(objs));
        }
    }

    @Test
    public void testGetNearbyHotelByScene() throws GaoDeException {
        Scene scene = new Scene();
        scene.setName("鼓浪屿");
        scene.setLocation("118.06702,24.444695");
        List<Object[]> list = sceneService.getNearbyHotelByScene(scene, 1000);
        for (Object[] objs:list) {
            System.out.println(Arrays.toString(objs));
        }
    }
}