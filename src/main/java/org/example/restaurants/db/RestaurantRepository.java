package org.example.restaurants.db;


import org.example.restaurants.model.Restaurant;
import org.example.shared.io.db.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class RestaurantRepository implements Repository<Restaurant> {
    @Override
    public Restaurant save(Restaurant restaurant) {
        return null;
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return Optional.empty();
    }


}
