# Blog API

## 결과 JAR 파일


```
정상적인 어플리케이션 부팅을 위해 내부에 EmbededRedis를 함께 동작 시킵니다.
EmbeddedRedis 의 기본 포트는 36379 로 설정 했으며 충돌시 
자바 옵션으로 spring.redis.port 를 설정해야 합니다 
```

## 사용 기술
* java 11
* spring boot 2.7.9
* h2
* jpa
* redisson
* mapstruct
* embedded-redis

## API Spec

### Search blog

### Get To 10 Keyword List 


## 문제해결 전략
* 확장성
* 동시성

### 대규모 트래픽에 대한 고민