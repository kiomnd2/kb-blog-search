package kb.kiomnd2.kbblogsearch.service;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchRequestDto;

public interface BlogApiClient {
    BlogSearchResultDto sendRequest(BlogSearchRequestDto blogSearchRequestDto);

}
