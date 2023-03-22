package kb.kiomnd2.kbblogsearch.domain.mapper.naver;

import kb.kiomnd2.kbblogsearch.common.enums.Sort;
import kb.kiomnd2.kbblogsearch.domain.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.domain.mapper.BlogMapper;
import kb.kiomnd2.kbblogsearch.domain.naver.NaverBlogRequestDto;
import kb.kiomnd2.kbblogsearch.domain.naver.NaverBlogResponseDto;
import kb.kiomnd2.kbblogsearch.interfaces.BlogSearchRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;


@Mapper(uses = {ItemMapper.class})
public interface NaverMapper extends BlogMapper<NaverBlogResponseDto, NaverBlogRequestDto> {

    NaverMapper INSTANCE = Mappers.getMapper(NaverMapper.class);

    @Mappings({
            @Mapping(source = "keyword", target = "query"),
            @Mapping(source = "sort", target = "sort", qualifiedByName = "sortToString"),
            @Mapping(source = "offset", target = "start"),
            @Mapping(source = "limit", target = "display")
    })
    NaverBlogRequestDto fromRequest(BlogSearchRequestDto requestDto);

    @Named("documentsToItems")
    @Mappings({
            @Mapping(source = "total", target = "totalCount"),
            @Mapping(source = "items", target = "items"),
            @Mapping(target = "isEnd", ignore = true)
    })
    @Override
    BlogSearchResultDto toResponse(NaverBlogResponseDto request);

    @Named("sortToString")
    static String sortToString(Sort sort) {
        if (sort == null) return "sim";
        if (sort.equals(Sort.ACCURACY)) return "sim";
        else if (sort.equals(Sort.RECENCY)) return "date";
        return null;
    }
}
