package kb.kiomnd2.kbblogsearch.domain.mapper.entity;

import kb.kiomnd2.kbblogsearch.domain.SearchDto;
import kb.kiomnd2.kbblogsearch.domain.mapper.EntityMapper;
import kb.kiomnd2.kbblogsearch.domain.SearchEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SearchMapper extends EntityMapper<SearchDto, SearchEntity> {
    SearchMapper INSTANCE = Mappers.getMapper(SearchMapper.class);
    SearchDto toDto(SearchEntity entity);
    List<SearchDto> toListDto(List<SearchEntity> list);
}
