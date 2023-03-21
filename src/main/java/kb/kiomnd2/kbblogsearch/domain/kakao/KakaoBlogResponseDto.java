package kb.kiomnd2.kbblogsearch.domain.kakao;

import kb.kiomnd2.kbblogsearch.domain.ResponseMark;
import lombok.*;

import java.util.List;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KakaoBlogResponseDto implements ResponseMark {
    private MetaDto meta;
    private List<DocumentsDto> documents;
}
