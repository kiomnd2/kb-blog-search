package kb.kiomnd2.kbblogsearch.controller;

import kb.kiomnd2.kbblogsearch.codes.ErrorCode;
import kb.kiomnd2.kbblogsearch.dto.response.ResponseDto;
import kb.kiomnd2.kbblogsearch.exception.BlogException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class BlogControllerAdvice {

    // 400 request parameter 에러
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDto<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("[MethodArgumentNotValidException] cause = {}, errorMsg = {}", NestedExceptionUtils.getMostSpecificCause(e),
                NestedExceptionUtils.getMostSpecificCause(e).getMessage());
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        if (fieldError != null) {
            String message = "Request Error" + " "
                    + fieldError.getField()
                    + "=" + fieldError.getRejectedValue()
                    + " (" + fieldError.getDefaultMessage()
                    + ")";
            return ResponseDto.ofFail(message, ErrorCode.INVALID_PARAMETER);
        } else {
            return ResponseDto.ofFail(ErrorCode.INVALID_PARAMETER);
        }
    }

    // 시스템 이슈 X 비지니스 로직 에러 상황
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BlogException.class)
    public ResponseDto<String> baseExceptionHandler(BlogException e) {
        log.error("[BaseException] cause = {}, errorMsg = {}", NestedExceptionUtils.getMostSpecificCause(e),
                NestedExceptionUtils.getMostSpecificCause(e).getMessage());
        return ResponseDto.ofFail(e.getErrorCode());
    }


    // 시스템 예외 상황
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseDto<String> runtimeExceptionHandler(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return ResponseDto.ofFail(ErrorCode.SYSTEM_ERROR);
    }



}
