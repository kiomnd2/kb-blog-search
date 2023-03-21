package kb.kiomnd2.kbblogsearch.domain.adapter.impl;

import kb.kiomnd2.kbblogsearch.domain.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.domain.ResponseMark;
import kb.kiomnd2.kbblogsearch.domain.adapter.BlogResponseAdapter;
import kb.kiomnd2.kbblogsearch.domain.mapper.naver.NaverMapper;
import kb.kiomnd2.kbblogsearch.domain.naver.NaverBlogResponseDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
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
