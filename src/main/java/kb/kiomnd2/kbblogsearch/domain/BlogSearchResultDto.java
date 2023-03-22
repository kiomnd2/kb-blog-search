package kb.kiomnd2.kbblogsearch.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlogSearchResultDto {
    private final Integer totalCount;
    private final Boolean isEnd;
    private final List<BlogSearchItemDto> items;
}
