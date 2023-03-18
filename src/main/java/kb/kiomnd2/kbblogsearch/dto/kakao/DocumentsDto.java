package kb.kiomnd2.kbblogsearch.dto.kakao;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kb.kiomnd2.kbblogsearch.dto.converter.LocalDateTimeDeserializer;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DocumentsDto {

    private String title;

    private String contents;

    private String url;

    private String blogname;

    private String thumbnail;

    private String datetime;
}
