package kb.kiomnd2.kbblogsearch.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ResponseDto<T> {

    private final T result;

    public static <T> ResponseDto<T> of(T body) {
        return new ResponseDto<>(body);
    }
}
