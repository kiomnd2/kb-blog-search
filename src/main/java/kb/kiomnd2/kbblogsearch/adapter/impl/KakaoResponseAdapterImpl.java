package kb.kiomnd2.kbblogsearch.adapter.impl;

import kb.kiomnd2.kbblogsearch.adapter.BlogResponseAdapter;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.ResponseMark;
import kb.kiomnd2.kbblogsearch.dto.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.mapper.kakao.KakaoMapper;
import org.springframework.stereotype.Component;

@Component
public class KakaoResponseAdapterImpl implements BlogResponseAdapter {

    @Override
    public boolean supports(ResponseMark target) {
        return (target instanceof KakaoBlogResponseDto);
    }

    @Override
    public BlogSearchResultDto handle(ResponseMark kakaoBlogResponseDto) {
        KakaoBlogResponseDto response = (KakaoBlogResponseDto) kakaoBlogResponseDto;
        return KakaoMapper.INSTANCE.toResponse(response);
    }
}