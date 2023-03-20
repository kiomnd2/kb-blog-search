package kb.kiomnd2.kbblogsearch.aop;

import kb.kiomnd2.kbblogsearch.annotation.RedissonLock;
import kb.kiomnd2.kbblogsearch.codes.ErrorCode;
import kb.kiomnd2.kbblogsearch.exception.BlogException;
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

        String key = joinPoint.getArgs()[0].toString();

        RLock rock = redissonClient.getLock(key);

        try {
            boolean isPossible = rock.tryLock(redissonLock.waitTime(), redissonLock.leaseTime(), redissonLock.timeUnit());
            if (!isPossible) {
                throw new BlogException(ErrorCode.LOCK_ACQUISITION_ERROR);
            }

            log.info("Redisson Lock Key : {}", key);

            return redissonTransactionCallAspect.proceed(joinPoint);
        } catch (Exception e) {
            throw new InterruptedException();
        } finally {
            rock.unlock();
        }
    }


}
