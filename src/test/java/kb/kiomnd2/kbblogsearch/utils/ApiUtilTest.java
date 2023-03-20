package kb.kiomnd2.kbblogsearch.utils;

import kb.kiomnd2.kbblogsearch.enums.Sort;
import org.assertj.core.api.Assertions;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Field;

class ApiUtilTest {

    @org.junit.jupiter.api.Test
    void apiUtil_parameterParsingTest() throws Exception {

        String name = "김형익";
        Sort accuracy = Sort.ACCURACY;
        int num = 1;
        TestObject test = new TestObject(name, accuracy, num);
        MultiValueMap<String, String> multiValueMap = ApiUtil.parseParam(test);
        Field[] declaredFields = test.getClass().getDeclaredFields();

        for (Field declaredField : declaredFields) {
            String key = declaredField.getName();
            String value = multiValueMap.getFirst(key);
            if (key.equals("name")) {
                Assertions.assertThat(value).isEqualTo(name);
            } else if (key.equals("sort")) {
                Assertions.assertThat(value).isEqualTo(accuracy.getCodeName());
            } else {
                Assertions.assertThat(value).isEqualTo(String.valueOf(num));
            }
        }
    }


    static class TestObject {

        String name;

        Sort sort;

        Integer num;

        public TestObject(String name, Sort sort, Integer num) {
            this.name = name;
            this.sort = sort;
            this.num = num;
        }
    }

}