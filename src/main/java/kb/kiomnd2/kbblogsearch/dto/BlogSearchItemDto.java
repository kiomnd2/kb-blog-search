package kb.kiomnd2.kbblogsearch.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
public class BlogSearchItemDto {

    private final String title;

    private final String blogLink;

    private final String bloggerName;

    private final String contents;

    private final String createAt;

}
