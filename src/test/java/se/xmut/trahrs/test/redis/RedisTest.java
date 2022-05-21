package se.xmut.trahrs.test.redis;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void A() throws InterruptedException {
//插入单条数据
        redisTemplate.opsForValue().set("key1", "我是新信息");
        System.out.println(redisTemplate.opsForValue().get("key1"));
//插入单条数据（存在有效期）
        System.out.println("-----------------");
        redisTemplate.opsForValue().set("key2", "这是一条会过期的信息", 100, TimeUnit.SECONDS);//向redis里存入数据和设置缓存时间
        System.out.println(redisTemplate.hasKey("key2"));//检查key是否存在，返回boolean值
        System.out.println(redisTemplate.opsForValue().get("key2"));
        Thread.sleep(2000);
        System.out.println(redisTemplate.hasKey("key2"));//检查key是否存在，返回boolean值
        System.out.println(redisTemplate.opsForValue().get("key2"));
        System.out.println("-----------------");

    }

    @org.junit.Test
    public void testHash() {
        redisTemplate.opsForHash().put("test:hash", "id", 1);
        redisTemplate.opsForHash().put("test:hash", "username", "test");
        System.out.println(redisTemplate.opsForHash().get("test:hash", "id"));
        System.out.println(redisTemplate.opsForHash().get("test:hash", "username"));
    }

    @org.junit.Test
    public void testBoundOperation(){
        BoundListOperations operations = redisTemplate.boundListOps("test:ids");
        System.out.println(operations.index(0));
        System.out.println(operations.leftPush(101));
        System.out.println(operations.leftPush(101));
        System.out.println(operations.range(0, 2));
    }

    @org.junit.Test
    public void testTransactional(){
        Object obj = redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                BoundSetOperations op = redisTemplate.boundSetOps("test:tx");

                operations.multi();

                op.add("陈柏宇");
                op.add("马锦源");
                op.add("吴锦新");

                System.out.println(op.members());

                return operations.exec();
            }
        });
        System.out.println(obj);
    }
}
