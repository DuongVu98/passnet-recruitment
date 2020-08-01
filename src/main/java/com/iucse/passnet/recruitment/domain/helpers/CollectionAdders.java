package com.iucse.passnet.recruitment.domain.helpers;

import java.util.Collection;

public interface CollectionAdders<E> {
    boolean add(E e);
    boolean addAll(Collection<? extends E> c);
}
