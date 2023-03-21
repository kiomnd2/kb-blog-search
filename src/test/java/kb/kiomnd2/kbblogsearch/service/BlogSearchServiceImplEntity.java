package kb.kiomnd2.kbblogsearch.service;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchItemDto;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchRequestDto;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.controller.request.SearchDto;
import kb.kiomnd2.kbblogsearch.enums.Sort;
import kb.kiomnd2.kbblogsearch.exception.BlogException;
import kb.kiomnd2.kbblogsearch.jpa.entity.SearchEntity;
import kb.kiomnd2.kbblogsearch.jpa.repository.SearchRepository;
import kb.kiomnd2.kbblogsearch.service.impl.BlogSearchServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BlogSearchServiceImplEntity {

    @InjectMocks
    private BlogSearchServiceImpl blogSearchService;

    @Mock
    private ApiClientService client;

    @Mock
    private BlogDataProcessService blogDataProcessService;

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
                .limit(10)
                .offset(2)
                .sort(accuracy)
                .build();

        BlogSearchResultDto result = BlogSearchResultDto.builder()
                .totalCount(totalCount)
                .items(List.of(
                        new BlogSearchItemDto(title, blogLink, blogName, content, createAt)
                ))
                .build();

        given(client.request(any())).willReturn(result);

        BlogSearchResultDto search = blogSearchService.search(request);

        assertThat(search.getTotalCount()).isEqualTo(search.getTotalCount());
        assertThat(search.getItems().size()).isEqualTo(1);
        assertThat(search.getItems().get(0).getBloggerName()).isEqualTo(blogName);
        assertThat(search.getItems().get(0).getBlogLink()).isEqualTo(blogLink);
        assertThat(search.getItems().get(0).getCreateAt()).isEqualTo(createAt);
    }

    @DisplayName("블로그 검색 Service 리퀘스트중 오류 발생 - 실패")
    @Test
    void searchTest_RequestError_fail() throws Exception {
        String keyWord = "keyTest";
        Sort accuracy = Sort.ACCURACY;


        BlogSearchRequestDto request = BlogSearchRequestDto.builder()
                .keyword(keyWord)
                .limit(10)
                .offset(2)
                .sort(accuracy)
                .build();

        given(client.request(any())).willThrow(BlogException.class);

        assertThatThrownBy(() -> {
            blogSearchService.search(request);
        });
    }

    @DisplayName("많이 조회한 키워드 조회 - 성공")
    @Test
    void searchListTest_success() throws Exception {

        List<SearchEntity> list = List.of(new SearchEntity("test1", 5, LocalDateTime.now()),
                new SearchEntity("test2", 4, LocalDateTime.now()),
                new SearchEntity("test3", 3, LocalDateTime.now()),
                new SearchEntity("test4", 2, LocalDateTime.now()),
                new SearchEntity("test5", 1, LocalDateTime.now()));


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
