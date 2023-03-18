package kb.kiomnd2.kbblogsearch.dto.kakao;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class KakaoBlogResponseDto {

    private MetaDto meta;

    private List<DocumentsDto> documents;
}
