package kb.kiomnd2.kbblogsearch.domain;

import kb.kiomnd2.kbblogsearch.common.property.KakaoApiProperty;
import kb.kiomnd2.kbblogsearch.common.property.NaverApiProperty;
import kb.kiomnd2.kbblogsearch.domain.kakao.KakaoApiCallerImpl;
import kb.kiomnd2.kbblogsearch.domain.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.domain.naver.NaverApiCallerImpl;
import kb.kiomnd2.kbblogsearch.domain.naver.NaverBlogResponseDto;
import kb.kiomnd2.kbblogsearch.interfaces.BlogSearchRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@Profile("test")
@SpringBootTest
class ApiCallerTest {

    @Autowired
    KakaoApiCallerImpl kakaoApiClient;

    @Autowired
    NaverApiCallerImpl naverApiClient;

    @Autowired
    RestTemplate restTemplate;

    MockRestServiceServer mockServer;

    KakaoApiProperty kakaoApiProperty = new KakaoApiProperty();

    NaverApiProperty naverApiProperty = new NaverApiProperty();


    @BeforeEach
    void setup() {
        String url = "localhost:8080";
        Charset charset = StandardCharsets.UTF_8;
        setKakaoProperty(url, charset);
        setNaverProperty(url, charset);

        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    private void setKakaoProperty(String url, Charset charset) {
        String apiKey = "test";
        kakaoApiProperty.setCharset(charset);
        kakaoApiProperty.setUrl(url);
        kakaoApiProperty.setApiKey(apiKey);
    }

    private void setNaverProperty(String url, Charset charset) {
        String clientId = "clientId";
        String secretKey = "secretKey";
        naverApiProperty.setClientId(clientId);
        naverApiProperty.setCharset(charset);
        naverApiProperty.setSecret(secretKey);
        naverApiProperty.setUrl(url);
    }

    @DisplayName("카카오 API TEST")
    @Test
    void kakaoApiClientTest() {
        String keyword = "test";
        String body = "{\"documents\":[{\"blogname\":\"짧은머리 개발자\",\"contents\":\"이전에 쓰던 To Do List를 폐기하고, " +
                "NestJS MVC 환경에서 TDD를 수행하는 법을 작성하려 한다. 크게 Unit " +
                "\\u003cb\\u003eTest\\u003c/b\\u003e와 Integration \\u003cb\\u003eTest\\u003c/b\\u003e로 나누어서 연재할 예정이다. " +
                "간략한 MVC 흔히 서비스의 프론트엔드에서 발생하는 요청을 처리하기 위해 우리는 백엔드의 시스템을 MVC 디자인 패턴을 이용해 설계하곤 한다. " +
                "MVC 패턴을...\",\"datetime\":\"2023-03-10T21:59:55.000+09:00\"," +
                "\"thumbnail\":\"https://search4.kakaocdn.net/argon/130x130_85_c/L9EJ0FzI9iO\"" +
                ",\"title\":\"NestJS — \\u003cb\\u003eTest\\u003c/b\\u003e Driven Development (1)\"" +
                ",\"url\":\"http://dev-whoan.xyz/102\"}],\"meta\":{\"is_end\":false,\"pageable_count\":800" +
                ",\"total_count\":1824636}}\n";

        BlogSearchRequestDto request = BlogSearchRequestDto.builder()
                .keyword(keyword)
                .limit(1)
                .offset(1)
                .build();

        UriComponents uriComponents = kakaoApiClient.getUriComponent(request);

        mockServer.expect(requestTo(uriComponents.toUri()))
                        .andExpect(method(HttpMethod.GET))
                                .andRespond(withSuccess(body, MediaType.APPLICATION_JSON));

        KakaoBlogResponseDto responseDto = kakaoApiClient.sendRequest(request);
        assertThat(responseDto.getMeta().getIsEnd()).isFalse();
        assertThat(responseDto.getMeta().getPageableCount()).isEqualTo(800);
        assertThat(responseDto.getMeta().getTotalCount()).isEqualTo(1824636);
        assertThat(responseDto.getDocuments().get(0).getBlogname()).isEqualTo("짧은머리 개발자");
        assertThat(responseDto.getDocuments().get(0).getContents()).startsWith("이전에 쓰던 To Do List를 폐기하고");
        assertThat(responseDto.getDocuments().get(0).getUrl()).isEqualTo("http://dev-whoan.xyz/102");
        assertThat(responseDto.getDocuments().get(0).getDatetime()).isEqualTo("2023-03-10T21:59:55.000+09:00");
        assertThat(responseDto.getDocuments().get(0).getThumbnail()).isEqualTo("https://search4.kakaocdn.net/argon/130x130_85_c/L9EJ0FzI9iO");
        assertThat(responseDto.getDocuments().get(0).getTitle()).startsWith("NestJS —");
    }

    @DisplayName("네이버 API TEST")
    @Test
    void naverApiClientTest() {
        String keyword = "test";
        String body = "{\n" +
                "\t\"lastBuildDate\":\"Mon, 20 Mar 2023 21:11:04 +0900\",\n" +
                "\t\"total\":1563134,\n" +
                "\t\"start\":1,\n" +
                "\t\"display\":1,\n" +
                "\t\"items\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"title\":\"SPAC <b>TEST<\\/b> 결과\",\n" +
                "\t\t\t\"link\":\"https:\\/\\/blog.naver.com\\/elin21c\\/223049154320\",\n" +
                "\t\t\t\"description\":\"회사에서 영어교육을 받기 위해 SPAC^ <b>Test<\\/b> 를 진행했다. 21년-22년에 열심히 회사 영어교육을... " +
                "그리고 어제 SPAC <b>TEST<\\/b> 응시했는데 오늘 결과보니 IH1 이다. Opic 점수와 비교해놓은걸 보니 Opic 에서는 IM1 정도의... \",\n" +
                "\t\t\t\"bloggername\":\"Daily\",\n" +
                "\t\t\t\"bloggerlink\":\"blog.naver.com\\/elin21c\",\n" +
                "\t\t\t\"postdate\":\"20230319\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";

        BlogSearchRequestDto request = BlogSearchRequestDto.builder()
                .keyword(keyword)
                .limit(1)
                .offset(1)
                .build();

        UriComponents uriComponents = naverApiClient.getUriComponent(request);

        mockServer.expect(requestTo(uriComponents.toUri()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(body, MediaType.APPLICATION_JSON));

        NaverBlogResponseDto responseDto = naverApiClient.sendRequest(request);
        assertThat(responseDto.getTotal()).isEqualTo(1563134);
        assertThat(responseDto.getStart()).isEqualTo(1);
        assertThat(responseDto.getItems().get(0).getBloggername()).isEqualTo("Daily");
        assertThat(responseDto.getItems().get(0).getDescription()).startsWith("회사에서 영어교육을 받기 위해 SPAC");
        assertThat(responseDto.getItems().get(0).getLink()).isEqualTo("https://blog.naver.com/elin21c/223049154320");
        assertThat(responseDto.getItems().get(0).getPostdate()).isEqualTo("20230319");
        assertThat(responseDto.getItems().get(0).getTitle()).isEqualTo("SPAC <b>TEST</b> 결과");
    }



}