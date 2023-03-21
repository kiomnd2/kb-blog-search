package kb.kiomnd2.kbblogsearch.domain.adapter;

import kb.kiomnd2.kbblogsearch.domain.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.domain.ResponseMark;

public interface BlogResponseAdapter {

    boolean supports(ResponseMark target);

    BlogSearchResultDto handle(ResponseMark target);
}
