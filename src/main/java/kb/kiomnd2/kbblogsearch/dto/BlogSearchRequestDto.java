package kb.kiomnd2.kbblogsearch.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import kb.kiomnd2.kbblogsearch.dto.converter.SortDeserializer;
import kb.kiomnd2.kbblogsearch.enums.Sort;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BlogSearchRequestDto {

    @NotEmpty(message = "keyword 는 필수값입니다")
    private String keyword;

    // sort 옵션
    @JsonDeserialize(using = SortDeserializer.class)
    private Sort sort;

    // 시작위치
    @Min(value = 1, message = "offset은 1 이상 입력해야 합니다.")
    @Max(value = 50, message = "offset은 최대 50 이하의 정수여야 합니다")
    private Integer offset;

    @Min(value = 1, message = "limit은 1 이상 입력해야 합니다.")
    @Max(value = 50, message = "offset은 최대 50 이하의 정수여야 합니다.")
    private Integer limit;
}
