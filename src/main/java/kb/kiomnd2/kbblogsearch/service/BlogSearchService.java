package kb.kiomnd2.kbblogsearch.service;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchRequestDto;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.request.SearchDto;

import java.util.List;

public interface BlogSearchService {

    BlogSearchResultDto search(BlogSearchRequestDto request);

    List<SearchDto> getSearchList();
}
