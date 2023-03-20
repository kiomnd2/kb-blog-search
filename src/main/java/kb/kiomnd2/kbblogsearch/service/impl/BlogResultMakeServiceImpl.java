package kb.kiomnd2.kbblogsearch.service.impl;

import kb.kiomnd2.kbblogsearch.adapter.BlogResponseAdapter;
import kb.kiomnd2.kbblogsearch.codes.ErrorCode;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.maker.ResponseMark;
import kb.kiomnd2.kbblogsearch.exception.BlogException;
import kb.kiomnd2.kbblogsearch.service.BlogResultMakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BlogResultMakeServiceImpl implements BlogResultMakeService {

    private final List<BlogResponseAdapter> adapters;

    @Override
    public BlogSearchResultDto make(final ResponseMark target) {
        for (BlogResponseAdapter adapter : adapters) {
            if (adapter.supports(target)) {
                return adapter.handle(target);
            }
        }
        throw new BlogException(ErrorCode.BLOG_RESULT_MAKE_ERROR);
    }
}
