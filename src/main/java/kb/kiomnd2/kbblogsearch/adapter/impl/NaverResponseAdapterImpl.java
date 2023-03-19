package kb.kiomnd2.kbblogsearch.adapter.impl;

import kb.kiomnd2.kbblogsearch.adapter.BlogResponseAdapter;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.ResponseMark;
import kb.kiomnd2.kbblogsearch.dto.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.dto.naver.NaverBlogResponseDto;
import kb.kiomnd2.kbblogsearch.mapper.kakao.KakaoMapper;
import kb.kiomnd2.kbblogsearch.mapper.naver.NaverMapper;
import org.springframework.stereotype.Component;

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
