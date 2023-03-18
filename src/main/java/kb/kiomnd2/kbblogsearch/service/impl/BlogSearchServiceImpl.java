package kb.kiomnd2.kbblogsearch.service.impl;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.SearchRequestDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.KakaoBlogRequestDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.mapper.kakao.KakaoRequestMapper;
import kb.kiomnd2.kbblogsearch.service.BlogSearchService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BlogSearchServiceImpl implements BlogSearchService {

    private final KakaoBlogApiClient client;

    @Override
    public BlogSearchResultDto search(SearchRequestDto request) {
        try {
            KakaoBlogRequestDto requestDto = KakaoRequestMapper.INSTANCE.fromRequest(request);
            KakaoBlogResponseDto kakaoBlogResponseDto = client.sendRequest(requestDto);
            return KakaoRequestMapper.INSTANCE.toResponse(kakaoBlogResponseDto);
        } catch (Exception e) {
            System.out.println("here");
            e.printStackTrace();
            throw e;
        }
    }
}
