package kb.kiomnd2.kbblogsearch.dto.kakao;

import kb.kiomnd2.kbblogsearch.dto.ResponseMark;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class KakaoBlogResponseDto implements ResponseMark {

    private MetaDto meta;

    private List<DocumentsDto> documents;
}
