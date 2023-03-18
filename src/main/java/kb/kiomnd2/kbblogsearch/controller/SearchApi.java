package kb.kiomnd2.kbblogsearch.controller;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.Response;
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
    public Response<BlogSearchResultDto> searchBlog(@RequestBody SearchRequestDto request) {
        return Response.of(blogSearchService.search(request));
    }

}

