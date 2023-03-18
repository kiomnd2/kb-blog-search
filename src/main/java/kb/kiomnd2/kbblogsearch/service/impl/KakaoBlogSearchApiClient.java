package kb.kiomnd2.kbblogsearch.service.impl;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.SearchRequestDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.KakaoBlogRequestDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.mapper.kakao.KakaoRequestMapper;
import kb.kiomnd2.kbblogsearch.property.KakaoApiProperty;
import kb.kiomnd2.kbblogsearch.service.BlogSearchApiClient;
import kb.kiomnd2.kbblogsearch.utils.ApiUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class KakaoBlogSearchApiClient implements BlogSearchApiClient {

    private final RestTemplate restTemplate;

    private final KakaoApiProperty kakaoApiProperty;

    @Override
    public BlogSearchResultDto sendRequest(SearchRequestDto searchRequestDto) {

        try {
            KakaoBlogRequestDto requestDto = KakaoRequestMapper.INSTANCE.fromRequest(searchRequestDto);

            UriComponents uriComponents = UriComponentsBuilder.fromUriString(kakaoApiProperty.getUrl())
                    .queryParams(ApiUtil.parseParam(requestDto))
                    .encode(kakaoApiProperty.getCharset())
                    .build();

            KakaoBlogResponseDto response = restTemplate.getForObject(uriComponents.toUri(), KakaoBlogResponseDto.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
