package kb.kiomnd2.kbblogsearch.application;

import kb.kiomnd2.kbblogsearch.domain.ApiClientService;
import kb.kiomnd2.kbblogsearch.domain.BlogDataProcessService;
import kb.kiomnd2.kbblogsearch.domain.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.domain.SearchDto;
import kb.kiomnd2.kbblogsearch.domain.mapper.entity.SearchMapper;
import kb.kiomnd2.kbblogsearch.infrastructure.SearchRepository;
import kb.kiomnd2.kbblogsearch.interfaces.BlogSearchRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogSearchFacade {

    private final SearchRepository searchRepository;
    private final BlogDataProcessService dataProcessService;
    private final ApiClientService apiClientService;

    public BlogSearchResultDto search(BlogSearchRequestDto request) {
        BlogSearchResultDto result = apiClientService.request(request);
        dataProcessService.processData(request.getKeyword());
        return result;
    }
    public List<SearchDto> getSearchList() {
        return SearchMapper.INSTANCE.toListDto(searchRepository.findTop10ByOrderByCountDesc());
    }
}
