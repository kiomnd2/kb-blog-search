package kb.kiomnd2.kbblogsearch.interfaces;

import kb.kiomnd2.kbblogsearch.application.BlogSearchFacade;
import kb.kiomnd2.kbblogsearch.common.response.ResponseDto;
import kb.kiomnd2.kbblogsearch.domain.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.domain.KeywordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/search")
public class BlogSearchApi {

    private final BlogSearchFacade blogSearchService;

    @PostMapping("/blog")
    public ResponseDto<BlogSearchResultDto> searchBlog(@Valid @RequestBody BlogSearchRequestDto request) {
        return ResponseDto.ofSuccess(blogSearchService.search(request));
    }

    @GetMapping("/blog/list")
    public ResponseDto<List<KeywordDto>> searchBlogList() {
        return ResponseDto.ofSuccess(blogSearchService.getSearchList());
    }
}

