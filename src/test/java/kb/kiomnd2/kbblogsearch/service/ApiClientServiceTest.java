package kb.kiomnd2.kbblogsearch.service;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchItemDto;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchRequestDto;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.DocumentsDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.MetaDto;
import kb.kiomnd2.kbblogsearch.dto.naver.NaverBlogResponseDto;
import kb.kiomnd2.kbblogsearch.dto.naver.NaverBlogResponseItemDto;
import kb.kiomnd2.kbblogsearch.exception.BlogException;
import kb.kiomnd2.kbblogsearch.service.impl.ApiClient;
import kb.kiomnd2.kbblogsearch.service.impl.ApiClientServiceImpl;
import kb.kiomnd2.kbblogsearch.service.impl.BlogResultMakeServiceImpl;
import kb.kiomnd2.kbblogsearch.service.impl.naver.NaverApiClientImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@Transactional
@ExtendWith(MockitoExtension.class)
class ApiClientServiceTest {

    @InjectMocks
    ApiClientServiceImpl apiClientService;

    @Mock
    BlogResultMakeServiceImpl blogResultMakeService;

    @Mock
    ApiClient<KakaoBlogResponseDto> client;

    @Spy
    List<ErrorHandleService> errorHandleServices = new ArrayList<>();

    @Mock
    NaverApiClientImpl naverApiClient;

    @BeforeEach
    void setup() {
        errorHandleServices.add(naverApiClient);
    }

    @DisplayName("Api 호출 및 Request Mapping - 성공")
    @Test
    void requestTest_success() {
        errorHandleServices.add(naverApiClient);

        int totalCount = 10;
        String blogname = "블로그이름";
        String contents = "컨텐츠";
        String title = "타이틀";
        String url = "localhost:8080";
        String datetime = "20200101";

        BlogSearchResultDto result = BlogSearchResultDto.builder()
                .totalCount(totalCount)
                .items(List.of(
                        new BlogSearchItemDto(title, url, blogname, contents, datetime)
                ))
                .build();

        BlogSearchRequestDto request = BlogSearchRequestDto.builder()
                .keyword("keyword")
                .build();


        given(blogResultMakeService.make(any())).willReturn(result);

        BlogSearchResultDto request1 = apiClientService.request(request);

        assertThat(request1.getTotalCount()).isEqualTo(totalCount);
        assertThat(request1.getItems().get(0).getTitle()).isEqualTo(title);
        assertThat(request1.getItems().get(0).getContents()).isEqualTo(contents);
        assertThat(request1.getItems().get(0).getBlogLink()).isEqualTo(url);
        assertThat(request1.getItems().get(0).getCreateAt()).isEqualTo(datetime);
        assertThat(request1.getItems().get(0).getBloggerName()).isEqualTo(blogname);
    }

    @DisplayName("Api 호출 및 Request Mapping - 실패 후 errorHadler 호출")
    @Test
    void requestTest_fail_requestErrorHandler() {
        int totalCount = 10;
        String blogname = "블로그이름";
        String contents = "컨텐츠";
        String title = "타이틀";
        String url = "localhost:8080";
        String datetime = "20200101";

        BlogSearchRequestDto request = BlogSearchRequestDto.builder()
                .keyword("keyword")
                .build();

        NaverBlogResponseDto naverResponse = NaverBlogResponseDto.builder()
                .total(totalCount)
                .items(List.of(NaverBlogResponseItemDto.builder()
                        .postdate(datetime)
                        .title(title)
                        .bloggername(blogname)
                        .bloggerlink(url)
                        .description(contents)
                        .build()))
                .build();

        BlogSearchResultDto result = BlogSearchResultDto.builder()
                .totalCount(totalCount)
                .items(List.of(
                        new BlogSearchItemDto(title, url, blogname, contents, datetime)
                ))
                .build();


        given(client.sendRequest(any())).willThrow(RuntimeException.class);
        given(naverApiClient.handle(request)).willReturn(naverResponse);
        given(blogResultMakeService.make(any())).willReturn(result);

        BlogSearchResultDto response = apiClientService.request(request);

        assertThat(response.getTotalCount()).isEqualTo(totalCount);
        assertThat(response.getItems().get(0).getTitle()).isEqualTo(title);
        assertThat(response.getItems().get(0).getContents()).isEqualTo(contents);
        assertThat(response.getItems().get(0).getBlogLink()).isEqualTo(url);
        assertThat(response.getItems().get(0).getCreateAt()).isEqualTo(datetime);
        assertThat(response.getItems().get(0).getBloggerName()).isEqualTo(blogname);
    }

}