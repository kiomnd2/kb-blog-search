package kb.kiomnd2.kbblogsearch.domain.naver;

import kb.kiomnd2.kbblogsearch.common.consts.NaverApiHeaderConst;
import kb.kiomnd2.kbblogsearch.common.property.NaverApiProperty;
import kb.kiomnd2.kbblogsearch.common.utils.ApiUtil;
import kb.kiomnd2.kbblogsearch.domain.ApiCaller;
import kb.kiomnd2.kbblogsearch.domain.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.domain.mapper.naver.NaverMapper;
import kb.kiomnd2.kbblogsearch.interfaces.BlogSearchRequestDto;
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
@RequiredArgsConstructor
@Service
public class NaverApiCallerImpl implements ApiCaller<NaverBlogResponseDto>, BlogErrorHandleService {

    private final RestTemplate restTemplate;
    private final NaverApiProperty naverApiProperty;

    @Override
    public NaverBlogResponseDto handle(BlogSearchRequestDto blogSearchResultDto) {
        return sendRequest(blogSearchResultDto);
    }

    @Override
    public UriComponents getUriComponent(BlogSearchRequestDto requestDto) {
        return UriComponentsBuilder.fromUriString(naverApiProperty.getUrl())
                .queryParams(ApiUtil.parseParam(NaverMapper.INSTANCE.fromRequest(requestDto)))
                .encode(naverApiProperty.getCharset())
                .build();
    }

    @Override
    public NaverBlogResponseDto sendRequest(BlogSearchRequestDto request) {
        UriComponents uriComponents = getUriComponent(request);

        log.info("request URI : {}", uriComponents.toUri());

        HttpHeaders headers = new HttpHeaders();
        headers.set(NaverApiHeaderConst.HEADER_CLIENT_ID, naverApiProperty.getClientId());
        headers.set(NaverApiHeaderConst.HEADER_SECRET, naverApiProperty.getSecret());
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<KakaoBlogResponseDto> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(uriComponents.toUri(),
                        HttpMethod.GET,
                        entity,
                        NaverBlogResponseDto.class)
                .getBody();
    }
}
