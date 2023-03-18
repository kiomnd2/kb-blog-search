package kb.kiomnd2.kbblogsearch.service;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.SearchDto;
import kb.kiomnd2.kbblogsearch.dto.SearchRequestDto;

import java.util.List;

public interface BlogSearchService {

    BlogSearchResultDto search(SearchRequestDto request);

    List<SearchDto> getSearchList();
}
