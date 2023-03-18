package kb.kiomnd2.kbblogsearch.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import kb.kiomnd2.kbblogsearch.dto.converter.SortDeserializer;
import kb.kiomnd2.kbblogsearch.enums.Sort;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class SearchRequestDto {

    // 검색 키워드
    private final String keyword;

    // sort 옵션
    @JsonDeserialize(using = SortDeserializer.class)
    private final Sort sort;

    // 페이징 정보
    @JsonUnwrapped
    private final PageableDto pageable;
}
