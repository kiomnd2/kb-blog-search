package kb.kiomnd2.kbblogsearch.utils;

import kb.kiomnd2.kbblogsearch.enums.Sort;
import org.assertj.core.api.Assertions;
import org.springframework.context.annotation.Profile;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Field;

@Profile("test")
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
            switch (key) {
                case "name":
                    Assertions.assertThat(value).isEqualTo(name);
                    break;
                case "sort":
                    Assertions.assertThat(value).isEqualTo(accuracy.getCodeName());
                    break;
                case "num":
                    Assertions.assertThat(value).isEqualTo(String.valueOf(num));
                    break;
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