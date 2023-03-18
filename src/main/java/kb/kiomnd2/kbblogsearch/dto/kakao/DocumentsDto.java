package kb.kiomnd2.kbblogsearch.dto.kakao;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DocumentsDto {

    private String title;

    private String contents;

    private String url;

    private String blogname;

    private String thumbnail;

    private LocalDateTime datetime;
}
