package kb.kiomnd2.kbblogsearch.service.impl;

import kb.kiomnd2.kbblogsearch.annotation.ConditionalErrorHandleOnNaver;
import kb.kiomnd2.kbblogsearch.consts.NaverApiHeaderConst;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchRequestDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.dto.naver.NaverBlogResponseDto;
import kb.kiomnd2.kbblogsearch.mapper.naver.NaverMapper;
import kb.kiomnd2.kbblogsearch.property.NaverApiProperty;
import kb.kiomnd2.kbblogsearch.service.ErrorHandleService;
import kb.kiomnd2.kbblogsearch.utils.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@ConditionalErrorHandleOnNaver
@RequiredArgsConstructor
@Service
public class NaverErrorHandleServiceImpl implements ErrorHandleService {

    private final RestTemplate restTemplate;

    private final NaverApiProperty naverApiProperty;

    @Override
    public NaverBlogResponseDto handler(BlogSearchRequestDto request) {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(naverApiProperty.getUrl())
                .queryParams(ApiUtil.parseParam(NaverMapper.INSTANCE.fromRequest(request)))
                .encode(naverApiProperty.getCharset())
                .build();

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
