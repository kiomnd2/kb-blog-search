package kb.kiomnd2.kbblogsearch.dto.kakao;

import kb.kiomnd2.kbblogsearch.dto.maker.ResponseMark;
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
