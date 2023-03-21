package kb.kiomnd2.kbblogsearch.domain.kakao;

import kb.kiomnd2.kbblogsearch.common.property.KakaoApiProperty;
import kb.kiomnd2.kbblogsearch.common.utils.ApiUtil;
import kb.kiomnd2.kbblogsearch.domain.ApiClient;
import kb.kiomnd2.kbblogsearch.domain.mapper.kakao.KakaoMapper;
import kb.kiomnd2.kbblogsearch.interfaces.BlogSearchRequestDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class KakaoApiClientImpl implements ApiClient<KakaoBlogResponseDto> {

    private final RestTemplate restTemplate;
    private final KakaoApiProperty kakaoApiProperty;

    @Override
    public UriComponents getUriComponent(BlogSearchRequestDto requestDto) {
        return UriComponentsBuilder.fromUriString(kakaoApiProperty.getUrl())
                .queryParams(ApiUtil.parseParam(KakaoMapper.INSTANCE.fromRequest(requestDto)))
                .encode(kakaoApiProperty.getCharset())
                .build();
    }

    @Override
    public KakaoBlogResponseDto sendRequest(BlogSearchRequestDto blogSearchRequestDto) {
        UriComponents uriComponents = this.getUriComponent(blogSearchRequestDto);

        log.info("request URI : {}", uriComponents.toUri());

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, kakaoApiProperty.getApiKey());
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<KakaoBlogResponseDto> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(uriComponents.toUri(),
                        HttpMethod.GET,
                        entity,
                        KakaoBlogResponseDto.class)
                .getBody();

    }
}
