package kb.kiomnd2.kbblogsearch.service.impl;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchRequestDto;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.SearchDto;
import kb.kiomnd2.kbblogsearch.jpa.repository.SearchRepository;
import kb.kiomnd2.kbblogsearch.mapper.entity.SearchMapper;
import kb.kiomnd2.kbblogsearch.service.BlogApiClient;
import kb.kiomnd2.kbblogsearch.service.BlogDataProcessService;
import kb.kiomnd2.kbblogsearch.service.BlogSearchService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BlogSearchServiceImpl implements BlogSearchService {

    private final BlogApiClient client;

    private final SearchRepository searchRepository;

    private final BlogDataProcessService dataProcessService;

    @Transactional
    @Override
    public BlogSearchResultDto search(BlogSearchRequestDto request) {
        BlogSearchResultDto searchResultDto = client.sendRequest(request);
        dataProcessService.processData(request.getKeyword());
        return searchResultDto;
    }

    @Override
    public List<SearchDto> getSearchList() {
        return SearchMapper.INSTANCE.toListDto(searchRepository.findTop10ByOrderByCountDesc());
    }


}
