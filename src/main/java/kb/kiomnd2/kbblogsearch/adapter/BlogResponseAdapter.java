package kb.kiomnd2.kbblogsearch.adapter;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.ResponseMark;

public interface BlogResponseAdapter {

    boolean supports(ResponseMark target);

    BlogSearchResultDto handle(ResponseMark target);

}
