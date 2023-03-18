package kb.kiomnd2.kbblogsearch.service.impl;

import kb.kiomnd2.kbblogsearch.adapter.BlogResponseAdapter;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.ResponseMark;
import kb.kiomnd2.kbblogsearch.exception.BlogResultMakeException;
import kb.kiomnd2.kbblogsearch.service.BlogResultMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@RequiredArgsConstructor
@Component
public class BlogResultMakerImpl implements BlogResultMaker {

    private final Set<BlogResponseAdapter> adapters;

    @Override
    public BlogSearchResultDto make(final ResponseMark target) {
        for (BlogResponseAdapter adapter : adapters) {
            if (adapter.supports(target)) {
                return adapter.handle(target);
            }
        }
        throw new BlogResultMakeException();
    }
}