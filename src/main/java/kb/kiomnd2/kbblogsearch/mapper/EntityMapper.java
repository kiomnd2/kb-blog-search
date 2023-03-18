package kb.kiomnd2.kbblogsearch.mapper;

public interface EntityMapper<D,E> {

    D toDto(E entity);

}
