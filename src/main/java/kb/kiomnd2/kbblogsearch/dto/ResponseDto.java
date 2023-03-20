package kb.kiomnd2.kbblogsearch.dto;

import kb.kiomnd2.kbblogsearch.codes.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResponseDto<T> {

    private Result result;

    private T data;

    private String errorCode;


    public static <T> ResponseDto<T> ofSuccess(T body) {
        return new ResponseDto<>(Result.SUCCESS, body, null );
    }


    public static <T> ResponseDto<T> ofFail(T message, ErrorCode code) {
        return new ResponseDto<>(Result.FAIL, message, code.name());
    }

    public static ResponseDto<String> ofFail(ErrorCode code) {
        return new ResponseDto<>(Result.FAIL, code.getErrorMessage(), code.name());
    }


    public enum Result {
        SUCCESS, FAIL
    }
}
