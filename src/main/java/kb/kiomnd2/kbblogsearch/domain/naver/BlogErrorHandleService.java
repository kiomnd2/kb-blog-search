package kb.kiomnd2.kbblogsearch.domain.naver;

import kb.kiomnd2.kbblogsearch.domain.ResponseMark;
import kb.kiomnd2.kbblogsearch.interfaces.BlogSearchRequestDto;

public interface BlogErrorHandleService {
    ResponseMark handle(BlogSearchRequestDto blogSearchResultDto);
}
