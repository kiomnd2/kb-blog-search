package kb.kiomnd2.kbblogsearch.jpa.repository;

import kb.kiomnd2.kbblogsearch.jpa.entity.SearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SearchRepository extends JpaRepository<SearchEntity, Long> {

    Optional<SearchEntity> findByKeyword(String keyword);

    List<SearchEntity> findTop10ByOrderByCountDesc();
}
