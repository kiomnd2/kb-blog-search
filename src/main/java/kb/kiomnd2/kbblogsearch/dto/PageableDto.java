package kb.kiomnd2.kbblogsearch.dto;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PageableDto {

    // 시작위치
    private final Integer offset;

    // 제한위치
    private final Integer limit;
}
