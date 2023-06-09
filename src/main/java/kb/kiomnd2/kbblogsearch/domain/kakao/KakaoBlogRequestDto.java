package kb.kiomnd2.kbblogsearch.domain.kakao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@AllArgsConstructor
public class KakaoBlogRequestDto {
    private String query;
    private String sort;
    private Integer page;
    private Integer size;
}
