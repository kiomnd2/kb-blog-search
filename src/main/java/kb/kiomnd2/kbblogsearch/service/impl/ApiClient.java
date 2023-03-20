package kb.kiomnd2.kbblogsearch.service.impl;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchRequestDto;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.KakaoBlogResponseDto;
import org.springframework.web.util.UriComponents;

public interface ApiClient<R> {

    UriComponents getUriComponent(BlogSearchRequestDto requestDto);

    R sendRequest(BlogSearchRequestDto blogSearchRequestDto);

}
