package kb.kiomnd2.kbblogsearch.domain.mapper.kakao;

import kb.kiomnd2.kbblogsearch.common.enums.Sort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static kb.kiomnd2.kbblogsearch.common.enums.Sort.ACCURACY;
import static kb.kiomnd2.kbblogsearch.common.enums.Sort.RECENCY;


@ExtendWith(MockitoExtension.class)
class KakaoMapperTest {


    @Test
    void mapToSortTest1() {
        String s = KakaoMapper.sortToString(ACCURACY);
        Assertions.assertThat(s).isEqualTo(ACCURACY.getCodeName());
    }

    @Test
    void mapToSortTest2() {
        String s = KakaoMapper.sortToString(Sort.RECENCY);
        Assertions.assertThat(s).isEqualTo(RECENCY.getCodeName());
    }

    @Test
    void mapToSortTest3() {
        String s = KakaoMapper.sortToString(null);
        Assertions.assertThat(s).isEqualTo(ACCURACY.getCodeName());
    }
}