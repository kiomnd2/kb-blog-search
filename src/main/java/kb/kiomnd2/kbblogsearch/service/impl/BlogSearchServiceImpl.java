package kb.kiomnd2.kbblogsearch.service.impl;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchItemDto;
import kb.kiomnd2.kbblogsearch.dto.SearchRequestDto;
import kb.kiomnd2.kbblogsearch.service.BlogSearchService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BlogSearchServiceImpl implements BlogSearchService {

    @Override
    public List<BlogSearchItemDto> search(SearchRequestDto request) {


        return null;
    }
}
