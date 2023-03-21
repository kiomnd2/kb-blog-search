package kb.kiomnd2.kbblogsearch.domain.search;

import kb.kiomnd2.kbblogsearch.domain.*;
import kb.kiomnd2.kbblogsearch.domain.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.domain.naver.NaverApiClientImpl;
import kb.kiomnd2.kbblogsearch.interfaces.BlogSearchRequestDto;
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

import static org.assertj.core.api.Assertions.assertThat;
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
    List<BlogErrorHandleService> errorHandleServices = new ArrayList<>();

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

}