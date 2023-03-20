package kb.kiomnd2.kbblogsearch.service;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchRequestDto;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;

public interface ApiClientService {
    BlogSearchResultDto request(BlogSearchRequestDto request);
}
