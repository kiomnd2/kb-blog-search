package kb.kiomnd2.kbblogsearch.service.impl;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchRequestDto;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.ResponseMark;
import kb.kiomnd2.kbblogsearch.exception.BlogApiRequestException;
import kb.kiomnd2.kbblogsearch.service.BlogResultMakeService;
import kb.kiomnd2.kbblogsearch.service.BlogSearchErrorProcessor;
import kb.kiomnd2.kbblogsearch.service.ErrorHandleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class BlogSearchErrorProcessorImpl implements BlogSearchErrorProcessor {

    private final Set<ErrorHandleService> errorHandleService;

    private final BlogResultMakeService resultMakeService;

    @Override
    public BlogSearchResultDto process(BlogSearchRequestDto request) {
        int errorCount = 0;
        for (ErrorHandleService errorHandleService : errorHandleService) {
            try {
                ResponseMark handler = errorHandleService.handler(request);
                return resultMakeService.make(handler);
            } catch (Exception e) {
                e.printStackTrace();
                errorCount++;
                if (errorCount < 3) {
                    continue;
                }
                throw new BlogApiRequestException();
            }
        }
        return BlogSearchResultDto.empty();
    }
}
