package kb.kiomnd2.kbblogsearch.service.impl.kakao;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchRequestDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.mapper.kakao.KakaoMapper;
import kb.kiomnd2.kbblogsearch.property.KakaoApiProperty;
import kb.kiomnd2.kbblogsearch.service.impl.ApiClient;
import kb.kiomnd2.kbblogsearch.service.BlogResultMakeService;
import kb.kiomnd2.kbblogsearch.utils.ApiUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Order(1)
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class KakaoApiClientImpl implements ApiClient<KakaoBlogResponseDto> {

    private final RestTemplate restTemplate;

    private final KakaoApiProperty kakaoApiProperty;

    private final BlogResultMakeService blogResultMakeService;

    @Override
    public KakaoBlogResponseDto sendRequest(BlogSearchRequestDto blogSearchRequestDto) {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(kakaoApiProperty.getUrl())
                .queryParams(ApiUtil.parseParam(KakaoMapper.INSTANCE.fromRequest(blogSearchRequestDto)))
                .encode(kakaoApiProperty.getCharset())
                .build();

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
