package kb.kiomnd2.kbblogsearch.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Sort {
    ACCURACY("accuracy"), RECENCY("recency");

    private final String codeName;

    public static Sort getSortByCode(final String code) {
        if (code == null) return Sort.ACCURACY;
        Sort[] values = values();
        return Arrays.stream(values).filter(sort -> code.equalsIgnoreCase(sort.codeName)).findFirst().orElse(ACCURACY);
    }
}
