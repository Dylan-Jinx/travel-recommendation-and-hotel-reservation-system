package se.xmut.trahrs.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.xmut.trahrs.domain.model.Scene;
import se.xmut.trahrs.exception.GaoDeException;

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

    @Test
    public void getNearestHotel() throws GaoDeException {
        List<Scene> list = new ArrayList<>();
//        Scene scene = new Scene();
//        scene.setName("皓月园");
//        scene.setLocation("118.075936,24.441099");
//        list.add(scene);
        Scene scene2 = new Scene();
        scene2.setName("鼓浪屿");
        scene2.setLocation("118.06702,24.444695");
        list.add(scene2);
        sceneService.getNearestHotel(list, 1000);
    }
}