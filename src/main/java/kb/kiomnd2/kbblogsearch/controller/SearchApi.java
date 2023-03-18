package kb.kiomnd2.kbblogsearch.controller;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.ResponseDto;
import kb.kiomnd2.kbblogsearch.dto.SearchRequestDto;
import kb.kiomnd2.kbblogsearch.service.BlogSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SearchApi {

    private final BlogSearchService blogSearchService;

    @PostMapping("/search/blog")
    public ResponseDto<BlogSearchResultDto> searchBlog(@RequestBody SearchRequestDto request) {
        return ResponseDto.of(blogSearchService.search(request));
    }

}

