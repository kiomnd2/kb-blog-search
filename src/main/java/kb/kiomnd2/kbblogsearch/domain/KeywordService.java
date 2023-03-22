package kb.kiomnd2.kbblogsearch.domain;

import java.util.List;

public interface KeywordService {
    List<KeywordDto> findTop10ByOrder();
}
