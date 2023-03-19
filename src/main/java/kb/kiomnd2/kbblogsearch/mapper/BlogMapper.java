package kb.kiomnd2.kbblogsearch.mapper;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchRequestDto;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;

public interface BlogMapper<T, R> {
    R fromRequest(BlogSearchRequestDto requestDto);

    BlogSearchResultDto toResponse(T request);
}
