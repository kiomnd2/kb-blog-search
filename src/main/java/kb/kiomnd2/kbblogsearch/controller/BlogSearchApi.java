package kb.kiomnd2.kbblogsearch.controller;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.ResponseDto;
import kb.kiomnd2.kbblogsearch.dto.SearchDto;
import kb.kiomnd2.kbblogsearch.dto.SearchRequestDto;
import kb.kiomnd2.kbblogsearch.service.BlogSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/search")
public class BlogSearchApi {

    private final BlogSearchService blogSearchService;

    @PostMapping("/blog")
    public ResponseEntity<ResponseDto<BlogSearchResultDto>> searchBlog(@RequestBody SearchRequestDto request) {
        return ResponseEntity.ok(ResponseDto.of(blogSearchService.search(request)));
    }

    @GetMapping("/blog/list")
    public ResponseEntity<ResponseDto<List<SearchDto>>> searchBlogList() {
        return ResponseEntity.ok(ResponseDto.of(blogSearchService.getSearchList()));
    }
}

