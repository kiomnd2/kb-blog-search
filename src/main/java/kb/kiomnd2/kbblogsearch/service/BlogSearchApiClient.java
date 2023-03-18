package kb.kiomnd2.kbblogsearch.service;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.SearchRequestDto;

public interface BlogSearchApiClient {
    BlogSearchResultDto sendRequest(SearchRequestDto searchRequestDto);

}
