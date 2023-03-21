package kb.kiomnd2.kbblogsearch.domain.mapper.kakao;

import kb.kiomnd2.kbblogsearch.common.enums.Sort;
import kb.kiomnd2.kbblogsearch.domain.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.domain.kakao.KakaoBlogRequestDto;
import kb.kiomnd2.kbblogsearch.domain.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.domain.mapper.BlogMapper;
import kb.kiomnd2.kbblogsearch.interfaces.BlogSearchRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;


@Mapper(uses = {DocumentMapper.class})
public interface KakaoMapper extends BlogMapper<KakaoBlogResponseDto, KakaoBlogRequestDto> {

    KakaoMapper INSTANCE = Mappers.getMapper(KakaoMapper.class);

    @Mappings({
            @Mapping(source = "keyword", target = "query"),
            @Mapping(source = "sort", target = "sort", qualifiedByName = "sortToString"),
            @Mapping(source = "offset", target = "page"),
            @Mapping(source = "limit", target = "size")
    })
    KakaoBlogRequestDto fromRequest(BlogSearchRequestDto requestDto);

    @Named("documentsToItems")
    @Mappings({
            @Mapping(source = "meta.totalCount", target = "totalCount"),
            @Mapping(source = "documents", target = "items")
    })
    @Override
    BlogSearchResultDto toResponse(KakaoBlogResponseDto request);

    @Named("sortToString")
    static String sortToString(Sort sort) {
        if (sort == null) return "accuracy";
        return sort.getCodeName();
    }
}
