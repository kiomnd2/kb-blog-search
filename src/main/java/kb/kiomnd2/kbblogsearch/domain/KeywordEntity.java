package kb.kiomnd2.kbblogsearch.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class KeywordEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String keyword;
    private long count;
    private LocalDateTime createAt;

    @Builder
    public KeywordEntity(String keyword, long count, LocalDateTime createAt) {
        this.keyword = keyword;
        this.count = count;
        this.createAt = createAt;
    }

    public void plusCount() {
        this.count++;
    }
}
