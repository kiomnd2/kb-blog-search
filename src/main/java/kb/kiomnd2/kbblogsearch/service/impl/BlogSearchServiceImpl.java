package kb.kiomnd2.kbblogsearch.service.impl;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchRequestDto;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.request.SearchDto;
import kb.kiomnd2.kbblogsearch.jpa.repository.SearchRepository;
import kb.kiomnd2.kbblogsearch.mapper.entity.SearchMapper;
import kb.kiomnd2.kbblogsearch.service.ApiClientService;
import kb.kiomnd2.kbblogsearch.service.BlogDataProcessService;
import kb.kiomnd2.kbblogsearch.service.BlogSearchService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BlogSearchServiceImpl implements BlogSearchService {

    private final SearchRepository searchRepository;

    private final BlogDataProcessService dataProcessService;

    private final ApiClientService apiClientService;


    @Override
    public BlogSearchResultDto search(BlogSearchRequestDto request) {
        BlogSearchResultDto result = apiClientService.request(request);
        dataProcessService.processData(request.getKeyword());
        return result;
    }

    @Override
    public List<SearchDto> getSearchList() {
        return SearchMapper.INSTANCE.toListDto(searchRepository.findTop10ByOrderByCountDesc());
    }


}
