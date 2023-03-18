package kb.kiomnd2.kbblogsearch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Slf4j
@Configuration(proxyBeanMethods = false)
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .setReadTimeout(Duration.ofMillis(30000))
                .setConnectTimeout(Duration.ofMillis(30000))
                .interceptors(new LoggingRequestInterceptor())
                .build();
    }

    static public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            traceRequest(request, body);

            ClientHttpResponse response = execution.execute(request, body);

            traceResponse(response, request.getURI());

            return response;
        }

        private void traceRequest(HttpRequest request, byte[] body) {
            String reqLog = "[REQUEST] " +
                    "Uri : " + request.getURI() +
                    ", Header : " + request.getHeaders() +
                    ", Method : " + request.getMethod() +
                    ", Request Body : " + new String(body, StandardCharsets.UTF_8);
            System.out.println(reqLog);
        }

        private void traceResponse(ClientHttpResponse response, URI uri) throws IOException {
            String resLog = "[RESPONSE] " +
                    "Uri : " + uri +
                    ", Header : " + response.getHeaders() +
                    ", Status code : " + response.getStatusCode() +
                    ", Response Body : " + StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
            System.out.println(resLog);
        }
    }

}
