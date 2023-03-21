package kb.kiomnd2.kbblogsearch.domain.naver;

import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NaverBlogResponseItemDto {

    private String title;

    private String link;

    private String description;

    private String bloggername;

    private String bloggerlink;

    private String postdate;
}
