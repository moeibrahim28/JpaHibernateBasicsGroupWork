package org.example.sports.db;

import org.example.shared.io.db.Repository;
import org.example.sports.model.Athlete;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Optional;

public class AthleteRepository implements Repository<Athlete> {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    // access transaction object
    EntityTransaction transaction = entityManager.getTransaction();
    @Override
    public Athlete save(Athlete athlete) {
        transaction.begin();
        entityManager.persist(athlete);
        transaction.commit();
        return athlete;
    }

    @Override
    public Optional<Athlete> findById(Long id) {
//        String query = "SELECT A from Athlete A where A.playerid = " + id;
//        return entityManager.createQuery(query,
//                Athlete.class).getResultList();
    return null;
    }
}
