package kb.kiomnd2.kbblogsearch.controller;

import kb.kiomnd2.kbblogsearch.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BlogControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ResponseDto<String>> runtimeExceptionHandler(RuntimeException e) {
        e.printStackTrace();
        return ResponseEntity.ok(ResponseDto.of(e.getMessage()));
    }
}
