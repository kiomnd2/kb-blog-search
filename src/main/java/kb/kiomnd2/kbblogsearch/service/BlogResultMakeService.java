package kb.kiomnd2.kbblogsearch.service;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.ResponseMark;

public interface BlogResultMakeService {

    BlogSearchResultDto make(ResponseMark target);
}
