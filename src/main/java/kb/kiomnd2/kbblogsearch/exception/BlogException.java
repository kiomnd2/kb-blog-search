package kb.kiomnd2.kbblogsearch.exception;

import kb.kiomnd2.kbblogsearch.codes.ErrorCode;
import lombok.Getter;

@Getter
public class BlogException extends RuntimeException {

    private ErrorCode errorCode;

    public BlogException() {
    }

    public BlogException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }

    public BlogException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BlogException(String message, ErrorCode errorCode, Throwable t) {
        super(message, t);
        this.errorCode = errorCode;
    }
}
