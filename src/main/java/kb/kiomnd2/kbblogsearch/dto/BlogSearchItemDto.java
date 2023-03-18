package kb.kiomnd2.kbblogsearch.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
public class BlogSearchItemDto {

    private final String blogLink;

    private final String bloggerName;

    private final String createAt;

}
