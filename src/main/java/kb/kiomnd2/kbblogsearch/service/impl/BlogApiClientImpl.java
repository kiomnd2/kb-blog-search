package kb.kiomnd2.kbblogsearch.service.impl;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchRequestDto;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.mapper.kakao.KakaoMapper;
import kb.kiomnd2.kbblogsearch.property.KakaoApiProperty;
import kb.kiomnd2.kbblogsearch.service.BlogApiClient;
import kb.kiomnd2.kbblogsearch.service.BlogResultMakeService;
import kb.kiomnd2.kbblogsearch.service.BlogSearchErrorProcessor;
import kb.kiomnd2.kbblogsearch.utils.ApiUtil;
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
public class BlogApiClientImpl implements BlogApiClient {

    private final RestTemplate restTemplate;

    private final KakaoApiProperty kakaoApiProperty;

    private final BlogResultMakeService blogResultMakeService;

    private final BlogSearchErrorProcessor blogSearchErrorProcessor;

    @Override
    public BlogSearchResultDto sendRequest(BlogSearchRequestDto blogSearchRequestDto) {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(kakaoApiProperty.getUrl())
                .queryParams(ApiUtil.parseParam(KakaoMapper.INSTANCE.fromRequest(blogSearchRequestDto)))
                .encode(kakaoApiProperty.getCharset())
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, kakaoApiProperty.getApiKey());
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<KakaoBlogResponseDto> entity = new HttpEntity<>(headers);
        try {
            KakaoBlogResponseDto response = restTemplate.exchange(uriComponents.toUri(),
                            HttpMethod.GET,
                            entity,
                            KakaoBlogResponseDto.class)
                    .getBody();
            return blogResultMakeService.make(response);
        } catch (Exception e) {
            log.error("kakao Api server Error : {} " ,e.getMessage());
            return blogSearchErrorProcessor.process(blogSearchRequestDto);
        }
    }
}