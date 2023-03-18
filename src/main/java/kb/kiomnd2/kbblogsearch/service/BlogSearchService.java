package kb.kiomnd2.kbblogsearch.service;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchItemDto;
import kb.kiomnd2.kbblogsearch.dto.SearchRequestDto;

import java.util.List;

public interface BlogSearchService {

    List<BlogSearchItemDto> search(SearchRequestDto request);
}
