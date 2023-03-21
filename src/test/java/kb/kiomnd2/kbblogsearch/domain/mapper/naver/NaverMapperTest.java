package kb.kiomnd2.kbblogsearch.domain.mapper.naver;

import kb.kiomnd2.kbblogsearch.common.enums.Sort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static kb.kiomnd2.kbblogsearch.common.enums.Sort.ACCURACY;

@ExtendWith(MockitoExtension.class)
class NaverMapperTest {


    @Test
    void mapToSortTest1() {
        String s = NaverMapper.sortToString(ACCURACY);
        Assertions.assertThat(s).isEqualTo("sim");
    }

    @Test
    void mapToSortTest2() {
        String s = NaverMapper.sortToString(Sort.RECENCY);
        Assertions.assertThat(s).isEqualTo("date");
    }

    @Test
    void mapToSortTest3() {
        String s = NaverMapper.sortToString(null);
        Assertions.assertThat(s).isEqualTo("sim");
    }
}