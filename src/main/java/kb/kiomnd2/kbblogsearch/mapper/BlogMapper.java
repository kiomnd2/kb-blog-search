package kb.kiomnd2.kbblogsearch.mapper;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.SearchRequestDto;

public interface BlogMapper<T, R> {
    R fromRequest(SearchRequestDto requestDto);

    BlogSearchResultDto toResponse(T request);
}
