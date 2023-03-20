package kb.kiomnd2.kbblogsearch.service;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchRequestDto;
import kb.kiomnd2.kbblogsearch.dto.maker.ResponseMark;

public interface ErrorHandleService {

    ResponseMark handle(BlogSearchRequestDto blogSearchResultDto);

}
