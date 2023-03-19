package kb.kiomnd2.kbblogsearch.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import kb.kiomnd2.kbblogsearch.dto.converter.SortDeserializer;
import kb.kiomnd2.kbblogsearch.enums.Sort;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class BlogSearchRequestDto {

    // 검색 키워드
    private String keyword;

    // sort 옵션
    @JsonDeserialize(using = SortDeserializer.class)
    private Sort sort;

    // 시작위치
    private Integer offset;

    // 제한위치
    private Integer limit;
}
