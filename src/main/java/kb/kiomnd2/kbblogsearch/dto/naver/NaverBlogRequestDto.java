package kb.kiomnd2.kbblogsearch.dto.naver;

import kb.kiomnd2.kbblogsearch.enums.Sort;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class NaverBlogRequestDto {

    private String query;

    private String display;

    private String start;

    private String sort;
}
