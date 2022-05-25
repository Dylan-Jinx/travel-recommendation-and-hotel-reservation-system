package se.xmut.trahrs.service;

import org.apache.mahout.cf.taste.common.TasteException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void getItemBasedCFRecommendation() throws TasteException {
        System.out.println(itemBasedCfService.getItemBasedCFRecommendation(49L, 2));
    }

    @Test
    public void guessYouLike() throws TasteException {
        System.out.println(itemBasedCfService.guessYouLike(49L, 2));
    }
}