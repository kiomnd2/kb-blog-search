package kb.kiomnd2.kbblogsearch.domain;

import kb.kiomnd2.kbblogsearch.domain.mapper.entity.SearchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class KeywordServiceImpl implements KeywordService{

    private final KeywordRepository keywordRepository;

    @Override
    public List<KeywordDto> findTop10ByOrder() {
        return SearchMapper.INSTANCE.toListDto(keywordRepository.findTop10ByOrderByCountDesc());
    }
}
