package kb.kiomnd2.kbblogsearch.domain;

import kb.kiomnd2.kbblogsearch.common.codes.ErrorCode;
import kb.kiomnd2.kbblogsearch.common.exception.BlogException;
import kb.kiomnd2.kbblogsearch.domain.adapter.BlogResponseAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BlogResultMakeServiceImpl implements BlogResultMakeService {

    private final List<BlogResponseAdapter> adapters;

    @Override
    public BlogSearchResultDto makeResponse(ResponseMark target) {
        for (BlogResponseAdapter adapter : adapters) {
            if (adapter.supports(target)) {
                return adapter.handle(target);
            }
        }
        throw new BlogException(ErrorCode.BLOG_RESULT_MAKE_ERROR);
    }
}
