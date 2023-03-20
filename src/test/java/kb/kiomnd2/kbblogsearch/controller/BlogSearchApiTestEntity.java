package kb.kiomnd2.kbblogsearch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kb.kiomnd2.kbblogsearch.dto.*;
import kb.kiomnd2.kbblogsearch.dto.request.SearchDto;
import kb.kiomnd2.kbblogsearch.enums.Sort;
import kb.kiomnd2.kbblogsearch.service.BlogSearchService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BlogSearchApi.class)
class BlogSearchApiTestEntity {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private BlogSearchService blogSearchService;


    @DisplayName("블로그 검색 API- 성공")
    @Test
    void requestSearchBlog_success() throws Exception {
        int totalCount = 5;
        int pageableCount = 2;

        String blogLink = "link";
        String blogName = "blogName";
        String createAt = "20200101";
        String title = "title";
        String content = "content";


        BlogSearchRequestDto request = BlogSearchRequestDto.builder()
                .keyword("test")
                .sort(Sort.ACCURACY)
                .limit(pageableCount)
                .offset(totalCount)
                .build();
        // result

        BlogSearchResultDto result = BlogSearchResultDto.builder()
                .totalCount(totalCount)
                .pageableCount(pageableCount)
                .items(List.of(
                        new BlogSearchItemDto(title, blogLink, blogName, content, createAt)
                ))
                .build();

        given(blogSearchService.search(any())).willReturn(result);


        mockMvc.perform(post("/search/blog")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("data.totalCount").value(totalCount))
                .andExpect(jsonPath("result").value("SUCCESS"))
                .andExpect(jsonPath("data.pageableCount").value(pageableCount))
                .andExpect(jsonPath("data.items[0].blogLink").value(blogLink))
                .andExpect(jsonPath("data.items[0].bloggerName").value(blogName))
                .andExpect(jsonPath("data.items[0].createAt").value(createAt));
    }

    @DisplayName("블로그 목록 최신순 조회 API - 성공")
    @Test
    void requestSearchBlogTest_success() throws Exception {

        List<SearchDto> list = List.of(new SearchDto("test1", 5, LocalDateTime.now()),
                new SearchDto("test2", 4, LocalDateTime.now()),
                new SearchDto("test3", 3, LocalDateTime.now()),
                new SearchDto("test4", 2, LocalDateTime.now()),
                new SearchDto("test5", 1, LocalDateTime.now()));

        given(blogSearchService.getSearchList()).willReturn(list);

        mockMvc.perform(get("/search/blog/list"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
