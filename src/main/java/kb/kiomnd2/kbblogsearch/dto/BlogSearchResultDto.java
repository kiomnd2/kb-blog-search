package kb.kiomnd2.kbblogsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class BlogSearchResultDto {

    private final Integer totalCount;

    private final Integer pageableCount;

    private final List<BlogSearchItemDto> items;
}
