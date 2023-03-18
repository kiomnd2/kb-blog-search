package kb.kiomnd2.kbblogsearch.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PageableDto {

    // 시작위치
    private final Integer offset;

    // 제한위치
    private final Integer limit;
}
