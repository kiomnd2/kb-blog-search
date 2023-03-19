package kb.kiomnd2.kbblogsearch.service.impl;

import kb.kiomnd2.kbblogsearch.redis.domain.Search;
import kb.kiomnd2.kbblogsearch.redis.repository.SearchRedisRepository;
import kb.kiomnd2.kbblogsearch.service.BlogWordCacheService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BlogWordCacheServiceImpl implements BlogWordCacheService {

    private final SearchRedisRepository searchRedisRepository;

    @Override
    public void addWord(String word) {
        Search search = Search.builder()
                .keyword(word)
                .count(1)
                .build();
        searchRedisRepository.save(search);
    }
}
