package kb.kiomnd2.kbblogsearch.service;

import kb.kiomnd2.kbblogsearch.adapter.BlogResponseAdapter;
import kb.kiomnd2.kbblogsearch.adapter.impl.KakaoResponseAdapterImpl;
import kb.kiomnd2.kbblogsearch.adapter.impl.NaverResponseAdapterImpl;
import kb.kiomnd2.kbblogsearch.dto.kakao.DocumentsDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.MetaDto;
import kb.kiomnd2.kbblogsearch.service.impl.BlogResultMakeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    //@Test
    void blogResultMakerTest_success() throws Exception {

        String blogname = "블로그이름";
        String contents = "컨텐츠";
        String title = "타이틀";
        String url = "localhost:8080";
        String thumbnail = "섬네일";
        String datetime = "20200101";
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
                        .totalCount(50)
                        .pageableCount(29)
                        .build())
                .build();

        System.out.println("kakaoBlogResponseDto = " + adapters);

        blogResultMakeService.make(kakaoBlogResponseDto);
    }

}