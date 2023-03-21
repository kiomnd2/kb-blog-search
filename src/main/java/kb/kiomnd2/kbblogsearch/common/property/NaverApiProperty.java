package kb.kiomnd2.kbblogsearch.common.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.nio.charset.Charset;

@Getter
@Setter
@ConstructorBinding
@ConfigurationProperties(prefix = "naver.blog")
public class NaverApiProperty {
    private String url;
    private String clientId;
    private String secret;
    private Charset charset;
}
