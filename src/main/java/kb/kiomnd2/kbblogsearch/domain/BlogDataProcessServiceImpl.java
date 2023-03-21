package kb.kiomnd2.kbblogsearch.domain;

import kb.kiomnd2.kbblogsearch.common.annotation.RedissonLock;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BlogDataProcessServiceImpl implements BlogDataProcessService {

    private final SearchRepository searchRepository;

    @RedissonLock
    @Override
    public void processData(String keyword) {
        this.updateSearch(keyword);
    }

    @Transactional
    public void updateSearch(String keyword) {
        searchRepository.findByKeyword(keyword).ifPresentOrElse((search) ->{
            search.plusCount();
            searchRepository.save(search);
            }, () -> searchRepository.save(
                        SearchEntity.builder()
                                .keyword(keyword)
                                .count(1)
                                .createAt(LocalDateTime.now())
                                .build()));
    }
}
