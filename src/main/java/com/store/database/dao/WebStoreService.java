package com.store.database.dao;

import com.store.component.transaction.GetTransaction;
import com.store.exception.DoesNotExistException;
import org.springframework.transaction.annotation.Transactional;

@GetTransaction
public interface WebStoreService<T,ID> {

    @Transactional
    T create(T elem);

    @Transactional
    T update(T elem) throws DoesNotExistException;

    T get(ID id);

    Iterable<T> getAll();

    @Transactional
    void delete(ID id);
}
