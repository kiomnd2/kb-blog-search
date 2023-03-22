package kb.kiomnd2.kbblogsearch.domain;

import kb.kiomnd2.kbblogsearch.interfaces.BlogSearchRequestDto;
import org.springframework.web.util.UriComponents;

public interface ApiCaller<R> {
    UriComponents getUriComponent(BlogSearchRequestDto requestDto);
    R sendRequest(BlogSearchRequestDto blogSearchRequestDto);
}
