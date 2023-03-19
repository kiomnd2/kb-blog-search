package kb.kiomnd2.kbblogsearch.exception;

public class BlogApiRequestException extends RuntimeException{
    public BlogApiRequestException() {
        super("블로그 API 요청 중 오류 발생.");
    }
}
