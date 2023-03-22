package kb.kiomnd2.kbblogsearch.domain.handler;

import kb.kiomnd2.kbblogsearch.common.codes.ErrorCode;
import kb.kiomnd2.kbblogsearch.common.exception.BlogException;
import kb.kiomnd2.kbblogsearch.domain.BlogResultMakeService;
import kb.kiomnd2.kbblogsearch.domain.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.domain.naver.BlogErrorHandleService;
import kb.kiomnd2.kbblogsearch.interfaces.BlogSearchRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogErrorHandlerFactoryImpl implements BlogErrorHandlerFactory {

    private final List<BlogErrorHandleService> blogErrorHandleServices;

    private final BlogResultMakeService blogResultMakeService;

    @Override
    public BlogSearchResultDto createHandler(BlogSearchRequestDto request) {
        for (BlogErrorHandleService blogErrorHandleService : blogErrorHandleServices) {
            blogResultMakeService.makeResponse(blogErrorHandleService.handle(request));
        }
        throw new BlogException(ErrorCode.BLOG_RESULT_REQUEST_ERROR);
    }
}
