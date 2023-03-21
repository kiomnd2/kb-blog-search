package kb.kiomnd2.kbblogsearch.domain.mapper;

import kb.kiomnd2.kbblogsearch.domain.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.interfaces.BlogSearchRequestDto;

public interface BlogMapper<T, R> {
    R fromRequest(BlogSearchRequestDto requestDto);
    BlogSearchResultDto toResponse(T request);
}
