package org.example.snippets.db;

import org.example.shared.io.db.Repository;
import org.example.snippets.model.Snippet;

import javax.persistence.EntityManager;
import java.util.Optional;

public class SnippetsRepository implements Repository<Snippet> {
    @Override
    public Snippet save(Snippet snippet) {
        return null;
    }

    @Override
    public Optional<Snippet> findById(Long id) {
        return Optional.empty();
    }

}
