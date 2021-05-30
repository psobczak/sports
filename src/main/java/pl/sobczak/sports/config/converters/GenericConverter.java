package pl.sobczak.sports.config.converters;

import pl.sobczak.sports.dto.Dto;
import pl.sobczak.sports.models.Entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface GenericConverter<D extends Dto, E extends Entity> {

    E createFrom(D dto);

    D createFrom(E entity);

    default List<D> createFromEntities(final Collection<E> entities) {
        return entities.stream()
                .map(this::createFrom)
                .collect(Collectors.toList());
    }

    default List<E> createFromDtos(final Collection<D> dtos) {
        return dtos.stream()
                .map(this::createFrom)
                .collect(Collectors.toList());
    }
}
