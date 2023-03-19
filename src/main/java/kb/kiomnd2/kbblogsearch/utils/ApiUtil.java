package kb.kiomnd2.kbblogsearch.utils;

import kb.kiomnd2.kbblogsearch.enums.Sort;
import lombok.experimental.UtilityClass;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Field;

@UtilityClass
public class ApiUtil {

    public static MultiValueMap<String, String> parseParam(Object request) {
        Field[] declaredFields = request.getClass().getDeclaredFields();

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            try {
                String key = declaredField.getName();
                Object value = declaredField.get(request);
                if (value instanceof String) {
                    map.add(key, (String) value);
                } else if (value instanceof Sort) {
                    map.add(key, ((Sort) value).getCodeName());
                } else {
                    map.add(key, String.valueOf(value));
                }
            } catch (Exception ignore) {}
        }
        return map;
    }
}
