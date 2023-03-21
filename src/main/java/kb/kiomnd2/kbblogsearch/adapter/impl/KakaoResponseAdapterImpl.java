package kb.kiomnd2.kbblogsearch.adapter.impl;

import kb.kiomnd2.kbblogsearch.adapter.BlogResponseAdapter;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.dto.maker.ResponseMark;
import kb.kiomnd2.kbblogsearch.mapper.kakao.KakaoMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
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
