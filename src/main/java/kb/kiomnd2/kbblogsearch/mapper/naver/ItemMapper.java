package kb.kiomnd2.kbblogsearch.mapper.naver;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchItemDto;
import kb.kiomnd2.kbblogsearch.dto.naver.NaverBlogResponseItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    @Mappings({
            @Mapping(source = "bloggerlink", target = "blogLink"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "description", target = "contents"),
            @Mapping(source = "bloggername", target = "bloggerName"),
            @Mapping(source = "postdate", target = "createAt"),
    })
    BlogSearchItemDto toItem(NaverBlogResponseItemDto dto);
}
