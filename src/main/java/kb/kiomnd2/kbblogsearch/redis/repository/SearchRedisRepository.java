package kb.kiomnd2.kbblogsearch.redis.repository;

import kb.kiomnd2.kbblogsearch.redis.domain.Search;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SearchRedisRepository extends CrudRepository<Search, Long> {
    Optional<Search> findByKeyword(String keyword);
}
