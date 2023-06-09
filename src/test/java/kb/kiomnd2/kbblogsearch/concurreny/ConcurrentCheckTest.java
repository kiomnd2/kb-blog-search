package kb.kiomnd2.kbblogsearch.concurreny;

import kb.kiomnd2.kbblogsearch.domain.BlogDataProcessService;
import kb.kiomnd2.kbblogsearch.domain.KeywordEntity;
import kb.kiomnd2.kbblogsearch.domain.KeywordRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Profile("test")
@SpringBootTest
public class ConcurrentCheckTest {

    @Autowired
    BlogDataProcessService dataProcessService;

    @Autowired
    KeywordRepository repository;

    @BeforeEach
    void setup() {
    }

    @DisplayName("50 개 쓰레드로 동시성 테스트 - 성공 ")
    @Test
    void dataProcessService_50peopleTest() throws Exception {

        int numberOfThread = 50;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(numberOfThread);

        String keyword = "keyword";
        for (int i=0 ; i<numberOfThread ; i++) {
            executorService.submit(() -> {
                try {
                    dataProcessService.processData(keyword);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        KeywordEntity search = repository.findByKeyword(keyword).get();
        Assertions.assertThat(search.getCount()).isEqualTo(numberOfThread);
        Assertions.assertThat(search.getKeyword()).isEqualTo(keyword);

    }

}
