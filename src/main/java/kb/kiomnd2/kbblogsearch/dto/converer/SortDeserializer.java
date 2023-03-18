package kb.kiomnd2.kbblogsearch.dto.converer;


import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import kb.kiomnd2.kbblogsearch.enums.Sort;

import java.io.IOException;

public class SortDeserializer extends JsonDeserializer<Sort> {

    @Override
    public Sort deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        String value = p.getValueAsString(Sort.ACCURACY.name());
        return Sort.getSortByCode(value);
    }

}
