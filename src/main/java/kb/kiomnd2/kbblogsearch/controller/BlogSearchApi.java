package kb.kiomnd2.kbblogsearch.controller;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchRequestDto;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.ResponseDto;
import kb.kiomnd2.kbblogsearch.dto.SearchDto;
import kb.kiomnd2.kbblogsearch.service.BlogSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/search")
public class BlogSearchApi {

    private final BlogSearchService blogSearchService;

    @PostMapping("/blog")
    public ResponseDto<BlogSearchResultDto> searchBlog(@Valid @RequestBody BlogSearchRequestDto request) {
        return ResponseDto.ofSuccess(blogSearchService.search(request));
    }

    @GetMapping("/blog/list")
    public ResponseDto<List<SearchDto>> searchBlogList() {
        return ResponseDto.ofSuccess(blogSearchService.getSearchList());
    }
}

