package kb.kiomnd2.kbblogsearch.domain.adapter.impl;

import kb.kiomnd2.kbblogsearch.domain.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.domain.ResponseMark;
import kb.kiomnd2.kbblogsearch.domain.adapter.BlogResponseAdapter;
import kb.kiomnd2.kbblogsearch.domain.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.domain.mapper.kakao.KakaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class KakaoResponseAdapterImpl implements BlogResponseAdapter {

    @Override
    public boolean supports(ResponseMark target) {
        return (target instanceof KakaoBlogResponseDto);
    }

    @Override
    public BlogSearchResultDto handle(ResponseMark responseDto) {
        KakaoBlogResponseDto response = (KakaoBlogResponseDto) responseDto;
        return KakaoMapper.INSTANCE.toResponse(response);
    }
}
