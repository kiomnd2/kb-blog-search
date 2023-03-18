package kb.kiomnd2.kbblogsearch.service.impl;

import kb.kiomnd2.kbblogsearch.dto.SearchRequestDto;
import kb.kiomnd2.kbblogsearch.enums.Sort;
import kb.kiomnd2.kbblogsearch.service.BlogSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KakaoBlogApiClientTest {

    @Autowired
    BlogSearchService blogSearchService;

    @Test
    void test() {
        SearchRequestDto requestDto = SearchRequestDto.builder()
                .keyword("test")
                .sort(Sort.ACCURACY)
                .build();
        blogSearchService.search(requestDto);
    }

}