package kb.kiomnd2.kbblogsearch.adapter.impl;

import kb.kiomnd2.kbblogsearch.adapter.BlogResponseAdapter;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.maker.ResponseMark;
import kb.kiomnd2.kbblogsearch.dto.naver.NaverBlogResponseDto;
import kb.kiomnd2.kbblogsearch.mapper.naver.NaverMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class NaverResponseAdapterImpl implements BlogResponseAdapter {

    @Override
    public boolean supports(ResponseMark target) {
        return (target instanceof NaverBlogResponseDto);
    }

    @Override
    public BlogSearchResultDto handle(ResponseMark responseDto) {
        NaverBlogResponseDto response = (NaverBlogResponseDto) responseDto;
        return NaverMapper.INSTANCE.toResponse(response);
    }
}
