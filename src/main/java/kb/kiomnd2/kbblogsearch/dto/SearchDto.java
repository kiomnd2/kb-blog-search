package kb.kiomnd2.kbblogsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
public class SearchDto {

    private String keyword;

    private long count;

    private LocalDateTime createAt;

}
