package kb.kiomnd2.kbblogsearch.dto.kakao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class KakaoBlogResponseDto {

    private MetaDto meta;

    private List<DocumentsDto> documents;
}
