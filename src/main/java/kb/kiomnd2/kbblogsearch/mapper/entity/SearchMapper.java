package kb.kiomnd2.kbblogsearch.mapper.entity;

import kb.kiomnd2.kbblogsearch.dto.SearchDto;
import kb.kiomnd2.kbblogsearch.jpa.entity.Search;
import kb.kiomnd2.kbblogsearch.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SearchMapper extends EntityMapper<SearchDto, Search> {

    SearchMapper INSTANCE = Mappers.getMapper(SearchMapper.class);

    SearchDto toDto(Search entity);

    List<SearchDto> toListDto(List<Search> list);
}
