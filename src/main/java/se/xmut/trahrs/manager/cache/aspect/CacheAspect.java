package se.xmut.trahrs.manager.cache.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import se.xmut.trahrs.manager.cache.vo.CacheVo;
import se.xmut.trahrs.manager.cache.service.Cache;
import se.xmut.trahrs.manager.cache.annoation.Cacheable;
import se.xmut.trahrs.manager.cache.factory.CacheFactory;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class CacheAspect {

    @Autowired
    private CacheFactory cacheFactory;

    //拦截Cacheable注解
    @Pointcut("@annotation( se.xmut.trahrs.manager.cache.annoation.Cacheable)")
    public void doPointCut() {
    }

    @Around("doPointCut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        try {
            //获取缓存
            Object cache = getCache(jp);
            //如果缓存不为空直接返回值，不执行目标方法(jp.proceed())
            if (cache != null) {
                return cache;
            } else {
                //如果缓存为空，则执行目标方法，拿到方法返回值设置缓存
                Object result = jp.proceed();
                setCache(jp, result);
                return result;
            }
        } catch (Throwable e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    //获取缓存
    private Object getCache(ProceedingJoinPoint jp) {
        CacheVo cacheVo = getCacheItem(jp);
        Cache cache = cacheFactory.produce(cacheVo);
        return cache.getObject(cacheVo.getKey());
    }

    //设置缓存
    private void setCache(ProceedingJoinPoint jp, Object res) throws NoSuchFieldException {
        CacheVo cacheVo = getCacheItem(jp);
        Cache cache = cacheFactory.produce(cacheVo);
        cache.putObject(cacheVo.getKey(),res);
    }


    //如果参数中带有#，则将方法参数的值作为缓存key，否则为常量key
    //例如test(String Id),如果设置的key为Id，则缓存的key为Id，如果设置的key为#Id,则缓存的key为Id的值
    private CacheVo getCacheItem(ProceedingJoinPoint jp) {
        Class<?> targetCls = jp.getTarget().getClass();
        String group = targetCls.getName();
        MethodSignature ms = (MethodSignature) jp.getSignature();
        Method method = ms.getMethod();


        Cacheable annotation = method.getAnnotation(Cacheable.class);
        String cacheName = annotation.cacheName();
        String key = annotation.key();

        if (StringUtils.isEmpty(cacheName)) {
            cacheName = group;
        }

        String fieldName = "";
        boolean iSField = key.contains("#");
        if (iSField) {
            fieldName = key.replaceAll("#", "");
        }


        Object[] args = jp.getArgs();
        if (args.length == 0 || StringUtils.isEmpty(key)) {
            key = method.getName();
        }
        if (iSField) {
            String[] parameterNames = ms.getParameterNames();
            int index = Arrays.binarySearch(parameterNames, fieldName);
            Object object = args[index];
            key = String.valueOf(object);
        }
        return CacheVo.builder()
                .cacheName(cacheName)
                .key(key)
                .capacity(annotation.capacity())
                .type(annotation.type())
                .log(annotation.log())
                .sync(annotation.sync())
                .expire(annotation.expire())
                .ExpireTime(annotation.expireTime())
                .build();
    }
}
