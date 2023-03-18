package kb.kiomnd2.kbblogsearch.controller;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.ResponseDto;
import kb.kiomnd2.kbblogsearch.dto.SearchDto;
import kb.kiomnd2.kbblogsearch.dto.SearchRequestDto;
import kb.kiomnd2.kbblogsearch.service.BlogSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogSearchApi {

    private final BlogSearchService blogSearchService;

    @PostMapping("/search/blog")
    public ResponseDto<BlogSearchResultDto> searchBlog(@RequestBody SearchRequestDto request) {
        return ResponseDto.of(blogSearchService.search(request));
    }

    @GetMapping("/search/blog/list")
    public ResponseDto<List<SearchDto>> searchBlogList() {
        return ResponseDto.of(blogSearchService.getSearchList());
    }
}

