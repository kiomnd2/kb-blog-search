package kb.kiomnd2.kbblogsearch.dto.kakao;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MetaDto {

    private String totalCount;

    private Integer pageableCount;

    private Boolean isEnd;
}
