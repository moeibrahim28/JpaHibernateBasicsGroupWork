package org.example.sports.db;

import org.example.shared.io.db.Repository;
import org.example.sports.model.Athlete;
import org.example.sports.model.Statistic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class StatisticRepository implements Repository<Statistic> {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    // access transaction object
    EntityTransaction transaction = entityManager.getTransaction();
    @Override
    public Statistic save(Statistic statistic) {
        transaction.begin();
        entityManager.persist(statistic);
        transaction.commit();
        return statistic;
    }

    public Athlete save(Athlete athlete) {
        transaction.begin();
        entityManager.persist(athlete);
        transaction.commit();
        return athlete;
    }

    @Override
    public Optional<Statistic> findById(Long id) {
        return Optional.empty();
    }

    public List<Statistic> getListOfStatistics(Statistic statistic){
        return statistic.getAthlete().getStatisticList();

    }
}
