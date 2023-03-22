package kb.kiomnd2.kbblogsearch.domain.handler;

import kb.kiomnd2.kbblogsearch.domain.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.interfaces.BlogSearchRequestDto;

public interface BlogErrorHandlerFactory {
    BlogSearchResultDto createHandler(BlogSearchRequestDto request);
}
