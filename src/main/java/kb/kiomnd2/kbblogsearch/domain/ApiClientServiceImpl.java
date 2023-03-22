package kb.kiomnd2.kbblogsearch.domain;

import kb.kiomnd2.kbblogsearch.domain.handler.BlogErrorHandlerFactory;
import kb.kiomnd2.kbblogsearch.domain.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.interfaces.BlogSearchRequestDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class ApiClientServiceImpl implements ApiClientService {

    private final ApiCaller<KakaoBlogResponseDto> client;
    private final BlogErrorHandlerFactory blogErrorHandlerFactory;
    private final BlogResultMakeService blogResultMakeService;

    @Override
    public BlogSearchResultDto request(BlogSearchRequestDto request) {
        try {
            return blogResultMakeService.makeResponse(client.sendRequest(request));
        } catch (Exception e) {
            log.error(e.getMessage());
            return blogErrorHandlerFactory.createHandler(request);
        }
    }
}
