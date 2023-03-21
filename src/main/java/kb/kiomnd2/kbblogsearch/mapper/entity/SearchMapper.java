package kb.kiomnd2.kbblogsearch.mapper.entity;

import kb.kiomnd2.kbblogsearch.controller.request.SearchDto;
import kb.kiomnd2.kbblogsearch.jpa.entity.SearchEntity;
import kb.kiomnd2.kbblogsearch.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SearchMapper extends EntityMapper<SearchDto, SearchEntity> {

    SearchMapper INSTANCE = Mappers.getMapper(SearchMapper.class);

    SearchDto toDto(SearchEntity entity);

    List<SearchDto> toListDto(List<SearchEntity> list);
}
