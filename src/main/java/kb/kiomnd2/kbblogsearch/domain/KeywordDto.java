package kb.kiomnd2.kbblogsearch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@AllArgsConstructor
public class KeywordDto {
    private String keyword;
    private long count;
    private LocalDateTime createAt;
}
