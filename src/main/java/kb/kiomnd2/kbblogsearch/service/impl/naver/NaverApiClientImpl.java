package kb.kiomnd2.kbblogsearch.service.impl.naver;

import kb.kiomnd2.kbblogsearch.consts.NaverApiHeaderConst;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchRequestDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.dto.naver.NaverBlogResponseDto;
import kb.kiomnd2.kbblogsearch.mapper.naver.NaverMapper;
import kb.kiomnd2.kbblogsearch.property.NaverApiProperty;
import kb.kiomnd2.kbblogsearch.service.impl.ApiClient;
import kb.kiomnd2.kbblogsearch.service.ErrorHandleService;
import kb.kiomnd2.kbblogsearch.utils.ApiUtil;
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

@Order(2)
@Slf4j
@RequiredArgsConstructor
@Service
public class NaverApiClientImpl implements ApiClient<NaverBlogResponseDto>, ErrorHandleService {

    private final RestTemplate restTemplate;

    private final NaverApiProperty naverApiProperty;

    @Override
    public NaverBlogResponseDto handle(BlogSearchRequestDto blogSearchResultDto) {
        return sendRequest(blogSearchResultDto);
    }

    @Override
    public NaverBlogResponseDto sendRequest(BlogSearchRequestDto request) {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(naverApiProperty.getUrl())
                .queryParams(ApiUtil.parseParam(NaverMapper.INSTANCE.fromRequest(request)))
                .encode(naverApiProperty.getCharset())
                .build();

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
