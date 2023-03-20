package kb.kiomnd2.kbblogsearch.codes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    SYSTEM_ERROR("일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요."), // 장애 상황
    INVALID_PARAMETER("요청한 값이 올바르지 않습니다."),
    LOCK_ACQUISITION_ERROR("락 획득 중 오류가 발생했습니다.."),
    BLOG_RESULT_MAKE_ERROR("블로그 API 결과 처리중 오류가 발생했습니다."),
    BLOG_RESULT_REQUEST_ERROR("블로그 API 요청 중 오류가 발생했습니다.");


    private final String errorMessage;

    public String getErrorMessage(Object ... arg) {
        return String.format(errorMessage, arg);
    }
}
