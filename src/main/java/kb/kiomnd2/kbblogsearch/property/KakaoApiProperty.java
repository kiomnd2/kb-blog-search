package kb.kiomnd2.kbblogsearch.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.nio.charset.Charset;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "kakao.blog")
public class KakaoApiProperty {

    private final String url;

    private final String apiKey;

    private final Charset charset;
}
