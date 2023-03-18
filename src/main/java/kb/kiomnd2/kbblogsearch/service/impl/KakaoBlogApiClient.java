package kb.kiomnd2.kbblogsearch.service.impl;

import kb.kiomnd2.kbblogsearch.dto.kakao.KakaoBlogRequestDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.property.KakaoApiProperty;
import kb.kiomnd2.kbblogsearch.service.BlogApiClient;
import kb.kiomnd2.kbblogsearch.utils.ApiUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Primary
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class KakaoBlogApiClient implements BlogApiClient<KakaoBlogRequestDto, KakaoBlogResponseDto> {

    private final RestTemplate restTemplate;

    private final KakaoApiProperty kakaoApiProperty;

    @Override
    public KakaoBlogResponseDto sendRequest(KakaoBlogRequestDto searchRequestDto) {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(kakaoApiProperty.getUrl())
                .queryParams(ApiUtil.parseParam(searchRequestDto))
                .encode(kakaoApiProperty.getCharset())
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, kakaoApiProperty.getApiKey());
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<KakaoBlogResponseDto> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, entity ,KakaoBlogResponseDto.class)
                .getBody();
    }
}
