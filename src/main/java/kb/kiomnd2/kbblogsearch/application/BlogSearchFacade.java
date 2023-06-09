package kb.kiomnd2.kbblogsearch.application;

import kb.kiomnd2.kbblogsearch.domain.*;
import kb.kiomnd2.kbblogsearch.interfaces.BlogSearchRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogSearchFacade {

    private final KeywordService keywordService;
    private final BlogDataProcessService dataProcessService;
    private final ApiClientService apiClientService;

    public BlogSearchResultDto search(BlogSearchRequestDto request) {
        dataProcessService.processData(request.getKeyword());
        return apiClientService.request(request);
    }

    public List<KeywordDto> getSearchList() {
        return keywordService.findTop10ByOrder();
    }
}
