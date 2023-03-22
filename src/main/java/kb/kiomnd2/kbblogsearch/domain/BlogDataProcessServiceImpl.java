package kb.kiomnd2.kbblogsearch.domain;

import kb.kiomnd2.kbblogsearch.common.annotation.RedissonLock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BlogDataProcessServiceImpl implements BlogDataProcessService {

    private final KeywordRepository keywordRepository;

    @RedissonLock
    @Override
    public void processData(String keyword) {
        this.updateSearch(keyword);
    }

    public void updateSearch(String keyword) {
        keywordRepository.findByKeyword(keyword).ifPresentOrElse((search) ->{
            search.plusCount();
            keywordRepository.save(search);
            }, () -> keywordRepository.save(
                        KeywordEntity.builder()
                                .keyword(keyword)
                                .count(1)
                                .createAt(LocalDateTime.now())
                                .build()));
    }
}
