package kb.kiomnd2.kbblogsearch.mapper.entity;

import kb.kiomnd2.kbblogsearch.dto.SearchDto;
import kb.kiomnd2.kbblogsearch.jpa.entity.Search;
import kb.kiomnd2.kbblogsearch.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper
public interface SearchMapper extends EntityMapper<SearchDto, Search> {

    SearchDto toDto(Search entity);
}
