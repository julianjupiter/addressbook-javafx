package com.julianjupiter.addressbook.persistence.repository;

import com.julianjupiter.addressbook.persistence.PersistenceManager;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {

    default EntityManager entityManager() {
        return PersistenceManager.entityManager();
    }

    List<T> findAll();

    Optional<T> findById(ID id);

    T save(T t);

    void deleteById(ID id);
}
