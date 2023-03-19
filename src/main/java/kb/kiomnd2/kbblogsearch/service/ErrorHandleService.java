package kb.kiomnd2.kbblogsearch.service;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchRequestDto;
import kb.kiomnd2.kbblogsearch.dto.ResponseMark;

public interface ErrorHandleService {
    ResponseMark handler(BlogSearchRequestDto request);
}
