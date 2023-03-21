package kb.kiomnd2.kbblogsearch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class BlogSearchResultDto {
    private final Integer totalCount;
    private final List<BlogSearchItemDto> items;
}
