package kb.kiomnd2.kbblogsearch.service.impl;

import kb.kiomnd2.kbblogsearch.dto.*;
import kb.kiomnd2.kbblogsearch.enums.Sort;
import kb.kiomnd2.kbblogsearch.jpa.entity.Search;
import kb.kiomnd2.kbblogsearch.jpa.repository.SearchRepository;
import kb.kiomnd2.kbblogsearch.service.BlogApiClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BlogSearchServiceImplTest {

    @InjectMocks
    private BlogSearchServiceImpl blogSearchService;

    @Mock
    private BlogApiClient client;

    @Mock
    private SearchRepository searchRepository;


    @DisplayName("블로그 검색 Service - 성공")
    @Test
    void searchTest_success() throws Exception {

        String keyWord = "keyTest";
        Sort accuracy = Sort.ACCURACY;
        Integer totalCount = 10;
        Integer pageableCount = 1;

        String blogLink = "http://blogLink.com";
        String blogName = "blogName";
        String createAt = "20230319";
        String title = "title";
        String content = "content";

        BlogSearchRequestDto request = BlogSearchRequestDto.builder()
                .keyword(keyWord)
                .pageable(PageableDto.builder()
                        .limit(10)
                        .offset(2)
                        .build())
                .sort(accuracy)
                .build();


        BlogSearchResultDto result = BlogSearchResultDto.builder()
                .totalCount(totalCount)
                .pageableCount(pageableCount)
                .items(List.of(
                        new BlogSearchItemDto(title, blogLink, blogName, content, createAt)
                ))
                .build();

        given(client.sendRequest(any())).willReturn(result);

        BlogSearchResultDto search = blogSearchService.search(request);

        assertThat(search.getTotalCount()).isEqualTo(search.getTotalCount());
        assertThat(search.getPageableCount()).isEqualTo(pageableCount);
        assertThat(search.getItems().size()).isEqualTo(1);
        assertThat(search.getItems().get(0).getBloggerName()).isEqualTo(blogName);
        assertThat(search.getItems().get(0).getBlogLink()).isEqualTo(blogLink);
        assertThat(search.getItems().get(0).getCreateAt()).isEqualTo(createAt);
    }

    @DisplayName("많이 조회한 키워드 조회 - 성공")
    @Test
    void searchListTest_success() throws Exception {

        List<Search> list = List.of(new Search("test1", 5, LocalDateTime.now()),
                new Search("test2", 4, LocalDateTime.now()),
                new Search("test3", 3, LocalDateTime.now()),
                new Search("test4", 2, LocalDateTime.now()),
                new Search("test5", 1, LocalDateTime.now()));


        given(searchRepository.findTop10ByOrderByCountDesc()).willReturn(list);

        List<SearchDto> searchList = blogSearchService.getSearchList();

        assertThat(searchList.size()).isEqualTo(list.size());
        for (int i=0 ; i < list.size() ; i++) {
            assertThat(searchList.get(i).getKeyword()).isEqualTo(list.get(i).getKeyword());
            assertThat(searchList.get(i).getCreateAt()).isEqualTo(list.get(i).getCreateAt());
            assertThat(searchList.get(i).getCount()).isEqualTo(list.get(i).getCount());
        }
    }
}
