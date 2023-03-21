package kb.kiomnd2.kbblogsearch.domain.mapper;

public interface EntityMapper<D,E> {
    D toDto(E entity);
}
