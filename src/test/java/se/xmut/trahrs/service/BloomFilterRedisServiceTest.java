package se.xmut.trahrs.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author breeze
 * @date 2022/5/25 21:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class BloomFilterRedisServiceTest {

    @Autowired
    private BloomFilterRedisService<String> bloomFilterRedisService;

    @Test
    public void testBloomFilterRedis(){
        for (int i = 111; i < 1111; i+=111) {
            bloomFilterRedisService.addByBloomFilter("test", i+"");
        }
        for (int i = 111; i < 1111; i+=111) {
            System.out.println(bloomFilterRedisService.includeByBloomFilter("test", i+""));
        }
    }

}