package kb.kiomnd2.kbblogsearch.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class BlogSearchResultDto {

    private final Integer totalCount;

    private final Integer pageableCount;

    private final List<BlogSearchItemDto> items = new ArrayList<>();
}
