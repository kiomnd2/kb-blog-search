package kb.kiomnd2.kbblogsearch.exception;

public class BlogResultMakeException extends RuntimeException {

    public BlogResultMakeException() {
        super("블로그 API 결과 처리중 오류 발생.");
    }
}
