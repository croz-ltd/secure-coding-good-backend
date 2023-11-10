package net.croz.owasp.goodexample.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface CreateMapper<T, U> {

    U map(T var1);

    default List<U> mapToList(Collection<T> from) {
        return from.stream().map(this::map).collect(Collectors.toList());
    }

}