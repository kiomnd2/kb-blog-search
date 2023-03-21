package kb.kiomnd2.kbblogsearch.domain.mapper.entity;

import kb.kiomnd2.kbblogsearch.domain.KeywordDto;
import kb.kiomnd2.kbblogsearch.domain.KeywordEntity;
import kb.kiomnd2.kbblogsearch.domain.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SearchMapper extends EntityMapper<KeywordDto, KeywordEntity> {
    SearchMapper INSTANCE = Mappers.getMapper(SearchMapper.class);
    KeywordDto toDto(KeywordEntity entity);
    List<KeywordDto> toListDto(List<KeywordEntity> list);
}
