package kb.kiomnd2.kbblogsearch.domain;

import kb.kiomnd2.kbblogsearch.interfaces.BlogSearchRequestDto;

public interface ApiClientService {
    BlogSearchResultDto request(BlogSearchRequestDto request);
}
