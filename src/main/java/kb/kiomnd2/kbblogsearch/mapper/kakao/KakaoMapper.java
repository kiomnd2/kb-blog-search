package kb.kiomnd2.kbblogsearch.mapper.kakao;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchRequestDto;
import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.KakaoBlogRequestDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.enums.Sort;
import kb.kiomnd2.kbblogsearch.mapper.BlogMapper;
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
                    @Mapping(source = "pageable.offset", target = "page"),
                    @Mapping(source = "pageable.limit", target = "size")
            })
    KakaoBlogRequestDto fromRequest(BlogSearchRequestDto requestDto);


    @Named("documentsToItems")
    @Mappings({
            @Mapping(source = "meta.totalCount", target = "totalCount"),
            @Mapping(source = "meta.pageableCount", target = "pageableCount"),
            @Mapping(source = "documents", target = "items")
    })
    @Override
    BlogSearchResultDto toResponse(KakaoBlogResponseDto request);

    @Named("sortToString")
    static String sortToString(Sort sort) {
        return sort.getCodeName();
    }
}
