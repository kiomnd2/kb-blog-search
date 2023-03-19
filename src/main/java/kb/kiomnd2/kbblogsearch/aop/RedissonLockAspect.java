package kb.kiomnd2.kbblogsearch.aop;

import kb.kiomnd2.kbblogsearch.annotation.RedissonLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class RedissonLockAspect {

    private final RedissonClient redissonClient;

    private final RedissonTransactionCall redissonTransactionCallAspect;


    @Around("@annotation(kb.kiomnd2.kbblogsearch.annotation.RedissonLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RedissonLock redissonLock = method.getAnnotation(RedissonLock.class);

        String key = this.createKey(signature.getParameterNames(), joinPoint.getArgs(), redissonLock.key());

        RLock rock = redissonClient.getLock(key);

        try {
            boolean isPossible = rock.tryLock(redissonLock.waitTime(), redissonLock.leaseTime(), redissonLock.timeUnit());
            if (!isPossible) {
                return false;
            }

            log.info("Redisson Lock Key : {}", key);

            return redissonTransactionCallAspect.proceed(joinPoint);
        } catch (Exception e) {
            throw new InterruptedException();
        } finally {
            rock.unlock();
        }
    }

    private String createKey(String[] parameterNames, Object[] args, String key) {
        String resultKey = key;

        /* key = parameterName */
        for (int i = 0; i < parameterNames.length; i++) {
            if (parameterNames[i].equals(key)) {
                resultKey += args[i];
                break;
            }
        }

        return resultKey;
    }
}
