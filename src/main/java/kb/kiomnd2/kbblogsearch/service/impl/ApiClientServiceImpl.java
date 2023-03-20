package kb.kiomnd2.kbblogsearch.service.impl;

import kb.kiomnd2.kbblogsearch.codes.ErrorCode;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchRequestDto;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.exception.BlogException;
import kb.kiomnd2.kbblogsearch.service.ApiClientService;
import kb.kiomnd2.kbblogsearch.service.BlogResultMakeService;
import kb.kiomnd2.kbblogsearch.service.ErrorHandleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ApiClientServiceImpl implements ApiClientService {

    private final ApiClient<KakaoBlogResponseDto> client;

    private final List<ErrorHandleService> errorHandleServices;

    private final BlogResultMakeService blogResultMakeService;

    @Override
    public BlogSearchResultDto request(BlogSearchRequestDto request) {
        try {
            return blogResultMakeService.make(client.sendRequest(request));
        } catch (Exception e) {
            log.error(e.getMessage());
            for (ErrorHandleService errorHandleService : errorHandleServices) {
                return blogResultMakeService.make(errorHandleService.handle(request));
            }
            throw new BlogException(ErrorCode.BLOG_RESULT_REQUEST_ERROR);
        }
    }
}
