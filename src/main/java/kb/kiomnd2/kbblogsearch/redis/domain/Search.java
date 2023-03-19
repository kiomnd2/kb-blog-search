package kb.kiomnd2.kbblogsearch.redis.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@RedisHash("search")
public class Search {

    @Id
    private Long id;

    private final String keyword;

    private final long count;

    @Builder
    public Search(String keyword, long count) {
        this.keyword = keyword;
        this.count = count;
    }
}
