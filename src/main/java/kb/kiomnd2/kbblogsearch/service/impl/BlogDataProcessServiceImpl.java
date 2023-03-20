package kb.kiomnd2.kbblogsearch.service.impl;

import kb.kiomnd2.kbblogsearch.annotation.RedissonLock;
import kb.kiomnd2.kbblogsearch.jpa.domain.SearchEntity;
import kb.kiomnd2.kbblogsearch.jpa.repository.SearchRepository;
import kb.kiomnd2.kbblogsearch.service.BlogDataProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BlogDataProcessServiceImpl implements BlogDataProcessService {

    private final SearchRepository searchRepository;

    @RedissonLock
    @Override
    public void processData(String keyword) {
        this.updateSearch(keyword);
    }

    public void updateSearch(String keyword) {
        searchRepository.findByKeyword(keyword).ifPresentOrElse(SearchEntity::plusCount,
                () -> searchRepository.save(
                        SearchEntity.builder()
                                .keyword(keyword)
                                .count(1)
                                .createAt(LocalDateTime.now())
                                .build()));
    }
}
