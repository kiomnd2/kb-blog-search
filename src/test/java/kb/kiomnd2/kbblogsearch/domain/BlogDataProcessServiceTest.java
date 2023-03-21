package kb.kiomnd2.kbblogsearch.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BlogDataProcessServiceTest {

    @InjectMocks
    private BlogDataProcessServiceImpl blogDataProcessService;

    @Mock
    private SearchRepository searchRepository;

    ArgumentCaptor<SearchEntity> searchEntityArgumentCaptor;

    @BeforeEach
    void beforeEach() {
        searchEntityArgumentCaptor = ArgumentCaptor.forClass(SearchEntity.class);

    }

    @DisplayName("검색 키워드 카운트 업데이트, 기존에 등록된 키 없어서 새로생성 - 성공")
    @Test
    void updateSearch_notFoundKeywordAndInsert_success() throws Exception {
        String keyword = "keyword";

        given(searchRepository.findByKeyword(any())).willReturn(Optional.empty());

        blogDataProcessService.processData(keyword);

        // get Capture 1 회 호출
        verify(searchRepository, times(1)).save(searchEntityArgumentCaptor.capture());
        SearchEntity value = searchEntityArgumentCaptor.getValue();

        assertThat(value.getKeyword()).isEqualTo(keyword);
        assertThat(value.getCount()).isEqualTo(1);
    }

    @DisplayName("검색 키워드 카운트 업데이트, 기존에 등록된 키 있고 기존 카운트 + 1 - 성공")
    @Test
    void updateSearch_isKeywordAndUpdate() throws Exception {
        String keyword = "keyword";
        int count = 1;
        LocalDateTime now = LocalDateTime.now();
        SearchEntity search = SearchEntity.builder()
                .keyword(keyword)
                .count(count)
                .createAt(now)
                .build();

        given(searchRepository.findByKeyword(any())).willReturn(Optional.ofNullable(search));
        blogDataProcessService.processData(keyword);

        verify(searchRepository).save(searchEntityArgumentCaptor.capture());
        SearchEntity value = searchEntityArgumentCaptor.getValue();

        assertThat(value.getCreateAt()).isEqualTo(now);
        assertThat(value.getKeyword()).isEqualTo(keyword);
        assertThat(value.getCount()).isEqualTo(count+1);
    }
}