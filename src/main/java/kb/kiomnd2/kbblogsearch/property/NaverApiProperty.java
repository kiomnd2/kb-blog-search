package kb.kiomnd2.kbblogsearch.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.nio.charset.Charset;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "naver.blog")
public class NaverApiProperty {

    private final String url;

    private final String clientId;

    private final String secret;

    private final Charset charset;
}
