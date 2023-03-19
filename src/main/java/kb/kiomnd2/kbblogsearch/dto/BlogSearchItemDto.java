package kb.kiomnd2.kbblogsearch.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class BlogSearchItemDto {

    private final String title;

    private final String blogLink;

    private final String bloggerName;

    private final String contents;

    private final String createAt;

}
