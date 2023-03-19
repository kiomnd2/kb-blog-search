package kb.kiomnd2.kbblogsearch.mapper;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchRequestDto;

public interface BlogMapper<T, R> {
    R fromRequest(BlogSearchRequestDto requestDto);

    BlogSearchResultDto toResponse(T request);
}
