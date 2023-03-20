package kb.kiomnd2.kbblogsearch.service.impl;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchRequestDto;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.KakaoBlogResponseDto;

public interface ApiClient<R> {
    R sendRequest(BlogSearchRequestDto blogSearchRequestDto);

}
