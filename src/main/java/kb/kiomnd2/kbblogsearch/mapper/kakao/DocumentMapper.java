package kb.kiomnd2.kbblogsearch.mapper.kakao;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchItemDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.DocumentsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DocumentMapper {
    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    @Mappings({
            @Mapping(source = "url", target = "blogLink"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "contents", target = "contents"),
            @Mapping(source = "blogname", target = "bloggerName"),
            @Mapping(source = "datetime", target = "createAt"),
    })
    BlogSearchItemDto toItem(DocumentsDto dto);
}
