package org.example.shared.io.db;

import javax.persistence.EntityManager;
import java.util.Optional;

public interface Repository <T> {
    T save(T t);
    Optional<T> findById(Long id);
}
