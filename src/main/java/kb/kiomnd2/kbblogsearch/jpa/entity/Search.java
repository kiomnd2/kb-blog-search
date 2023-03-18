package kb.kiomnd2.kbblogsearch.jpa.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Search {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;

    private long count;

    private LocalDateTime createAt;

    @Version
    private Long version;

    @Builder
    public Search(String keyword, long count, LocalDateTime createAt) {
        this.keyword = keyword;
        this.count = count;
        this.createAt = createAt;
    }

    public void plusCount() {
        this.count++;
    }
}
