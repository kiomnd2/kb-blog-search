package kb.kiomnd2.kbblogsearch.common.exception;

import kb.kiomnd2.kbblogsearch.common.codes.ErrorCode;
import lombok.Getter;

@Getter
public class BlogException extends RuntimeException {

    private ErrorCode errorCode;

    public BlogException() {}

    public BlogException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }

    public BlogException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BlogException(ErrorCode errorCode, Throwable t) {
        super(errorCode.getErrorMessage(), t);
        this.errorCode = errorCode;
    }

    public BlogException(String message, ErrorCode errorCode, Throwable t) {
        super(message, t);
        this.errorCode = errorCode;
    }
}
