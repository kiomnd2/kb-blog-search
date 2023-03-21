package kb.kiomnd2.kbblogsearch.service;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchRequestDto;
import org.springframework.web.util.UriComponents;

public interface ApiClient<R> {

    UriComponents getUriComponent(BlogSearchRequestDto requestDto);

    R sendRequest(BlogSearchRequestDto blogSearchRequestDto);

}
