package kb.kiomnd2.kbblogsearch.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Sort {
    ACCURACY("accuracy"), RECENCY("recency");

    private final String codeName;

    public static Sort getSortByCode(final String code) {
        Sort[] values = values();
        return Arrays.stream(values).filter(sort -> code.equalsIgnoreCase(sort.codeName)).findFirst().orElse(ACCURACY);
    }
}
