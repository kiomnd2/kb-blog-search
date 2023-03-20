package kb.kiomnd2.kbblogsearch.adapter;

import kb.kiomnd2.kbblogsearch.adapter.impl.KakaoResponseAdapterImpl;
import kb.kiomnd2.kbblogsearch.adapter.impl.NaverResponseAdapterImpl;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.DocumentsDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.MetaDto;
import kb.kiomnd2.kbblogsearch.dto.naver.NaverBlogResponseDto;
import kb.kiomnd2.kbblogsearch.dto.naver.NaverBlogResponseItemDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ResponseAdapterTest {

    @InjectMocks
    KakaoResponseAdapterImpl kakaoResponseAdapter;

    @InjectMocks
    NaverResponseAdapterImpl naverResponseAdapter;

    @DisplayName("카카오 지원 클래스 테스트 - 성공")
    @Test
    void kakaoSupportsTest_success() {
        KakaoBlogResponseDto responseDto = KakaoBlogResponseDto.builder().build();

        boolean supports = kakaoResponseAdapter.supports(responseDto);

        assertThat(supports).isTrue();
    }

    @DisplayName("키카오 결과가 정확하게 데이터가 매핑되서 리턴되는지 테스트 - 성공")
    @Test
    void kakaoHandleTest_success() {
        String blogname = "블로그이름";
        String contents = "컨텐츠";
        String title = "타이틀";
        String url = "localhost:8080";
        String thumbnail = "섬네일";
        String datetime = "20200101";
        int totalCount = 50;
        int pageableCount = 29;
        KakaoBlogResponseDto kakaoBlogResponseDto = KakaoBlogResponseDto.builder()
                .documents(List.of(
                        DocumentsDto.builder()
                                .blogname(blogname)
                                .contents(contents)
                                .title(title)
                                .url(url)
                                .thumbnail(thumbnail)
                                .datetime(datetime)
                                .build()))
                .meta(MetaDto.builder()
                        .totalCount(totalCount)
                        .pageableCount(pageableCount)
                        .build())
                .build();


        BlogSearchResultDto handle = kakaoResponseAdapter.handle(kakaoBlogResponseDto);

        assertThat(handle.getTotalCount()).isEqualTo(totalCount);
        assertThat(handle.getItems().get(0).getBlogLink()).isEqualTo(url);
        assertThat(handle.getItems().get(0).getBloggerName()).isEqualTo(blogname);
        assertThat(handle.getItems().get(0).getContents()).isEqualTo(contents);
        assertThat(handle.getItems().get(0).getTitle()).isEqualTo(title);
        assertThat(handle.getItems().get(0).getCreateAt()).isEqualTo(datetime);
    }

    @DisplayName("네이버 지원 클래스 테스트 - 성공")
    @Test
    void naverSupportsTest_success() {
        NaverBlogResponseDto responseDto = NaverBlogResponseDto.builder().build();

        boolean supports = naverResponseAdapter.supports(responseDto);

        assertThat(supports).isTrue();
    }

    @DisplayName("네이버 결과가 정확하게 데이터가 매핑되서 리턴되는지 테스트 - 성공")
    @Test
    void naverHandleTest_success() {
        String blogname = "블로그이름";
        String contents = "컨텐츠";
        String title = "타이틀";
        String url = "localhost:8080";
        String thumbnail = "섬네일";
        String datetime = "20200101";
        int totalCount = 50;
        int pageableCount = 29;
        NaverBlogResponseDto naverBlogResponseDto = NaverBlogResponseDto.builder()
                .items(List.of(
                        NaverBlogResponseItemDto.builder()
                                .link(url)
                                .bloggername(blogname)
                                .description(contents)
                                .title(title)
                                .postdate(datetime)
                                .build()
                ))
                .total(totalCount)
                .start(pageableCount)
                .total(50)
                .build();


        BlogSearchResultDto handle = naverResponseAdapter.handle(naverBlogResponseDto);

        assertThat(handle.getTotalCount()).isEqualTo(totalCount);
        assertThat(handle.getItems().get(0).getBlogLink()).isEqualTo(url);
        assertThat(handle.getItems().get(0).getBloggerName()).isEqualTo(blogname);
        assertThat(handle.getItems().get(0).getContents()).isEqualTo(contents);
        assertThat(handle.getItems().get(0).getTitle()).isEqualTo(title);
        assertThat(handle.getItems().get(0).getCreateAt()).isEqualTo(datetime);
    }



}