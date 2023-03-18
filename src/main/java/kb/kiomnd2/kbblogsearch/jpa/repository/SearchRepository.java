package kb.kiomnd2.kbblogsearch.jpa.repository;

import kb.kiomnd2.kbblogsearch.jpa.entity.Search;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SearchRepository extends JpaRepository<Search, Long> {

    Optional<Search> findByKeyword(String keyword);
}
