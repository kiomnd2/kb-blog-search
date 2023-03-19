package kb.kiomnd2.kbblogsearch.dto.naver;

import kb.kiomnd2.kbblogsearch.dto.ResponseMark;
import lombok.*;

import java.util.List;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NaverBlogResponseDto implements ResponseMark {

    private Integer total;

    private Integer start;

    private List<NaverBlogResponseItemDto> items;

}
