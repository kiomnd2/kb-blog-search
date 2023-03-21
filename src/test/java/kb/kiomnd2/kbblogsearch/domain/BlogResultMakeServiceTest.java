package kb.kiomnd2.kbblogsearch.domain;

import kb.kiomnd2.kbblogsearch.domain.adapter.BlogResponseAdapter;
import kb.kiomnd2.kbblogsearch.domain.kakao.DocumentsDto;
import kb.kiomnd2.kbblogsearch.domain.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.domain.kakao.MetaDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BlogResultMakeServiceTest {

    @InjectMocks
    BlogResultMakeServiceImpl blogResultMakeService;

    @Spy
    List<BlogResponseAdapter> adapters = new ArrayList<>();

    @Mock
    BlogResponseAdapter blogResponseAdapter;



    @BeforeEach
    void setup() {
        adapters.add(blogResponseAdapter);
    }

    @DisplayName("블로그 결과 Make Test - 성공")
    @Test
    void blogResultMakerTest_success() throws Exception {
        String blogname = "블로그이름";
        String contents = "컨텐츠";
        String title = "타이틀";
        String url = "localhost:8080";
        String thumbnail = "섬네일";
        String datetime = "20200101";
        int totalCount = 50;
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
                        .pageableCount(29)
                        .build())
                .build();

        BlogSearchResultDto r = BlogSearchResultDto.builder()
                .totalCount(totalCount)
                .items(List.of(
                        BlogSearchItemDto.builder()
                                .blogLink(url)
                                .bloggerName(blogname)
                                .contents(contents)
                                .title(title)
                                .createAt(datetime)
                                .build()
                ))
                .build();

        given(blogResponseAdapter.supports(any())).willReturn(true);
        given(blogResponseAdapter.handle(any())).willReturn(r);

        BlogSearchResultDto result = blogResultMakeService.makeResponse(kakaoBlogResponseDto);

        Assertions.assertThat(result.getTotalCount()).isEqualTo(totalCount);
        assertThat(result.getItems().get(0).getBloggerName()).isEqualTo(blogname);
        assertThat(result.getItems().get(0).getBlogLink()).isEqualTo(url);
        assertThat(result.getItems().get(0).getCreateAt()).isEqualTo(datetime);
    }

}