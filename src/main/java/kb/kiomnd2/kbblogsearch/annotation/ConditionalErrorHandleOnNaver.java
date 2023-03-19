package kb.kiomnd2.kbblogsearch.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@ConditionalOnProperty(prefix = "error.handler", value = "service", havingValue = "naver")
public @interface ConditionalErrorHandleOnNaver {
}
