package kb.kiomnd2.kbblogsearch.domain;

import kb.kiomnd2.kbblogsearch.common.codes.ErrorCode;
import kb.kiomnd2.kbblogsearch.common.exception.BlogException;
import kb.kiomnd2.kbblogsearch.domain.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.interfaces.BlogSearchRequestDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class ApiClientServiceImpl implements ApiClientService {

    private final ApiClient<KakaoBlogResponseDto> client;
    private final List<BlogErrorHandleService> errorHandleServices;
    private final BlogResultMakeService blogResultMakeService;

    @Override
    public BlogSearchResultDto request(BlogSearchRequestDto request) {
        try {
            return blogResultMakeService.make(client.sendRequest(request));
        } catch (Exception e) {
            log.error(e.getMessage());
            for (BlogErrorHandleService errorHandleService : errorHandleServices) {
                return blogResultMakeService.make(errorHandleService.handle(request));
            }
            throw new BlogException(ErrorCode.BLOG_RESULT_REQUEST_ERROR);
        }
    }
}
